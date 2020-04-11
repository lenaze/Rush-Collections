package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (!isPossibleToColor(image, x, y)) return false;
        if (desiredColor == null || image[y][x] == desiredColor) return false;

        image = getImage(image, x, y, desiredColor);

        return true;
    }

    public Color[][] getImage(Color[][] image, int x, int y, Color desiredColor) {
        if (desiredColor == null || image[y][x] == desiredColor) return image;

        Color[][] currentImage = image.clone();
        Color startColor = image[y][x];
        currentImage[y][x] = desiredColor;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == j && j == 0) j++;
                if (isPossibleToColor(image, x + i, y + j)
                        && startColor == image[y + j][x + i]) {
                    currentImage = getImage(currentImage, x + i, y + j, desiredColor);
                    if (x + i == 0 && y + j == 1) System.out.println(currentImage[y + j][x + i]);
                }
            }
        }
        return currentImage;
    }

    protected boolean isPossibleToColor(Color[][] image, int x, int y) {
        try {
            return image[y][x] != null;
        } catch (ArrayIndexOutOfBoundsException exp) {
            return false;
        }
    }
}
