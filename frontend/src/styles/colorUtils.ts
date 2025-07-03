/**
 * Convierte un hex (#RRGGBB) en objeto RGB.
 */
function hexToRgb(hex: string) {
  const clean = hex.replace(/^#/, '');
  if (clean.length !== 6) throw new Error('hex inválido');
  const num = parseInt(clean, 16);
  return {
    r: (num >> 16) & 0xff,
    g: (num >>  8) & 0xff,
    b: num & 0xff,
  };
}

/**
 * Convierte un canal (0–255) en hex de dos dígitos.
 */
function channelToHex(c: number) {
  const h = c.toString(16);
  return h.length === 1 ? '0' + h : h;
}

/**
 * Convierte RGB al string hex.
 */
function rgbToHex(r: number, g: number, b: number) {
  return `#${channelToHex(r)}${channelToHex(g)}${channelToHex(b)}`;
}

/**
 * Aclara un color hex un porcentaje (e.g. 0.2 → 20% más claro).
 */
export function lighten(hex: string, amount: number): string {
  const { r, g, b } = hexToRgb(hex);
  const newR = Math.min(255, Math.floor(r + (255 - r) * amount));
  const newG = Math.min(255, Math.floor(g + (255 - g) * amount));
  const newB = Math.min(255, Math.floor(b + (255 - b) * amount));
  return rgbToHex(newR, newG, newB);
}

/**
 * Oscurece un color hex un porcentaje (e.g. 0.2 → 20% más claro).
 */
export function darken(hex: string, amount: number): string {
  const { r, g, b } = hexToRgb(hex);
  const newR = Math.max(0, Math.floor(r * (1 - amount)));
  const newG = Math.max(0, Math.floor(g * (1 - amount)));
  const newB = Math.max(0, Math.floor(b * (1 - amount)));
  return rgbToHex(newR, newG, newB);
}