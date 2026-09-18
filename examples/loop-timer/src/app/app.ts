import { Component, signal, computed, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

type Phase = 'idle' | 'activity' | 'rest' | 'done';

class Beep {
  private ctx: AudioContext | null = null;
  private getCtx() { if (!this.ctx) this.ctx = new (window.AudioContext || (window as any).webkitAudioContext)(); return this.ctx; }
  private vibrate(p: number | number[]) { if ('vibrate' in navigator) navigator.vibrate(p); }
  tone(f: number, d: number, v = 0.3, t: OscillatorType = 'sine') {
    const ctx = this.getCtx(); if (ctx.state === 'suspended') ctx.resume();
    const o = ctx.createOscillator(); const g = ctx.createGain();
    o.type = t; o.frequency.value = f; o.connect(g); g.connect(ctx.destination);
    g.gain.setValueAtTime(v, ctx.currentTime); g.gain.exponentialRampToValueAtTime(0.01, ctx.currentTime + d);
    o.start(); o.stop(ctx.currentTime + d);
  }
  activityStart() { this.tone(880, 0.15); setTimeout(() => this.tone(880, 0.15), 150); this.vibrate([100, 50, 100]); }
  restStart() { this.tone(440, 0.3); this.vibrate(200); }
  tick() { this.tone(1000, 0.08, 0.15, 'square'); this.vibrate(30); }
  done() { this.tone(523, 0.2); setTimeout(() => this.tone(659, 0.2), 150); setTimeout(() => this.tone(784, 0.4), 300); this.vibrate([100, 50, 100, 50, 300]); }
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnDestroy {
  repetitions = signal(15);
  activityLength = signal(45);
  restLength = signal(40);

  currentRep = signal(1);
  currentTime = signal(0);
  phase = signal<Phase>('idle');
  running = signal(false);

  totalSeconds = computed(() => {
    const reps = Math.max(1, Number(this.repetitions()) || 0);
    const act = Math.max(0, Number(this.activityLength()) || 0);
    const rest = Math.max(0, Number(this.restLength()) || 0);
    return reps * act + Math.max(0, reps - 1) * rest;
  });

  elapsedSeconds = computed(() => {
    if (this.phase() === 'idle') return 0;
    if (this.phase() === 'done') return this.totalSeconds();
    const r = this.currentRep();
    const act = this.activityLength();
    const rest = this.restLength();
    const t = this.currentTime();
    if (this.phase() === 'activity') {
      return (r - 1) * (act + rest) + (act - t);
    } else {
      return (r - 1) * (act + rest) + act + (rest - t);
    }
  });

  remainingSeconds = computed(() => Math.max(0, this.totalSeconds() - this.elapsedSeconds()));
  
  totalProgress = computed(() => {
    if (this.totalSeconds() === 0) return 0;
    return (this.elapsedSeconds() / this.totalSeconds()) * 100;
  });

  progress = computed(() => {
    const total = this.phase() === 'activity' ? this.activityLength() : this.restLength();
    if (!total || this.phase() === 'idle' || this.phase() === 'done') return 0;
    return (this.currentTime() / total) * 100;
  });

  private interval: any = null;
  private beeper = new Beep();

  start() {
    if (this.running()) return;
    if (this.phase() === 'idle' || this.phase() === 'done') {
      this.currentRep.set(1);
      this.startPhase('activity');
    }
    this.running.set(true);
    this.interval = setInterval(() => this.tick(), 1000);
  }

  pause() { this.running.set(false); clearInterval(this.interval); }
  
  reset() { this.pause(); this.phase.set('idle'); this.currentTime.set(0); this.currentRep.set(1); }

  private startPhase(p: Phase) {
    this.phase.set(p);
    this.currentTime.set(p === 'activity' ? this.activityLength() : this.restLength());
    if (p === 'activity') this.beeper.activityStart();
    if (p === 'rest') this.beeper.restStart();
    if (p === 'done') this.beeper.done();
    if (p === 'rest' && this.currentTime() === 0) this.nextPhase();
  }

  private tick() {
    if (this.currentTime() > 0) {
      this.currentTime.update(v => v - 1);
      if (this.currentTime() <= 3 && this.currentTime() > 0) this.beeper.tick();
    } else this.nextPhase();
  }

  private nextPhase() {
    if (this.phase() === 'activity') {
      if (this.currentRep() >= this.repetitions()) {
        this.phase.set('done'); this.beeper.done(); this.pause();
      } else this.startPhase('rest');
    } else if (this.phase() === 'rest') {
      this.currentRep.update(v => v + 1);
      this.startPhase('activity');
    }
  }

  ngOnDestroy() { clearInterval(this.interval); }

  format(s: number): string {
    const m = Math.floor(s / 60);
    const sec = s % 60;
    return m > 0 ? `${m}m ${sec}s` : `${sec}s`;
  }
}