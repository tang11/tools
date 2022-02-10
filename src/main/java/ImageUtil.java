import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author shenli
 * @date 2022/1/26
 */
public class ImageUtil {
    public static void main(String[] args) throws IOException {
        String fromPic = "";
        String toTxt = "";
        BufferedImage bufferedImage = ImageIO.read(new File(fromPic));
        // 1.压缩图片
        BufferedImage compactImage = Thumbnails.of(bufferedImage).size(908, 909).asBufferedImage();
        // 2.灰度化
        BufferedImage grayImage = grayingImage(compactImage);
        // 3.二值化
        BufferedImage binaryImage = binaryImage(grayImage);
        // 4.输出到txt文本
        writeToTxt(binaryImage, toTxt);


        /*保存图片
        File newFile = new File("d:\\test5.jpg");
        ImageIO.write(binaryImage, "jpg", newFile);*/
    }

    /**
     * 灰度化图片
     * @param bufferedImage 原图片
     * @return 灰度化之后的图片
     */
    private static BufferedImage grayingImage(BufferedImage bufferedImage) {
        BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                int color = bufferedImage.getRGB(i, j);
                grayImage.setRGB(i, j, color);
            }
        }
        return grayImage;
    }

    /**
     * 二值化图片
     * @param bufferedImage 原图片
     * @return 二值化后的图片
     */
    private static BufferedImage binaryImage(BufferedImage bufferedImage) {
        BufferedImage grayImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        int threshold = getMeanThreshold(bufferedImage);
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                int color = bufferedImage.getRGB(i, j);
                int r = (color >> 16) & 0xff;
                int g = (color >> 8) & 0xff;
                int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                if (gray > threshold) {
                    grayImage.setRGB(i, j, 0xFFFFFF);
                }
                else {
                    grayImage.setRGB(i, j, 0);
                }
            }
        }
        return grayImage;
    }

    /**
     * 获取图片的阀值，采用基于灰度平均值的阈值
     * @param bufferedImage 原图片
     * @return 二值化的阈值
     */
    private static int getMeanThreshold(BufferedImage bufferedImage) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int num = 0;
        int sum = 0;
        for(int i=0; i<w; i++) {
            for(int j = 0; j < h; j++) {
                int color = bufferedImage.getRGB(i, j);
                int r = (color >> 16) & 0xff;
                int g = (color >> 8) & 0xff;
                int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                sum += gray;
                num += 1;
            }
        }

        // 测试表明，阀值取平均值的1.2倍效果最好。
        int threshold = sum / num;
        if(threshold * 1.2 < 255) {
            threshold = (int)(1.2 * sum / num);
        }
        System.out.println("width: " + w + " height: " + h + " threshold: " + threshold);
        return threshold;
    }

    /**
     * 输出 0,1 TXT文本
     */
    public static void writeToTxt(BufferedImage bufferedImage, String toSaveFilePath) {
        File file = new File(toSaveFilePath);
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                for(int i = 0; i < bufferedImage.getWidth(); i++) {
                    int color = bufferedImage.getRGB(i, j);
                    if(color == -1) {
                        builder.append(" ");
                    }
                    else {
                        builder.append("0");
                    }
                }
                builder.append("\r\n");
            }
            writer.write(builder.toString());
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
