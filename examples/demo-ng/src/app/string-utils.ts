export class StringUtils {

  static hasText(s: string): boolean {
    return s != null && s.trim().length > 0;
  }

  static isString(value: any): boolean {
    return typeof value == 'string';
  }
}
