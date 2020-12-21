export function looseInvalid(key: string | number): boolean {
  return key === null || key === undefined || key === '';
}
