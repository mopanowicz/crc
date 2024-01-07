import { StringUtils } from './string-utils';

describe('StringUtils', () => {
  it('should have text', () => {
    expect(StringUtils.hasText('abc')).toBeTruthy();
  });

  it('should be string', () => {
    expect(StringUtils.isString('abc')).toBeTruthy();
  });
});
