package org.daybreak.coccinella.webmagic;

import org.apache.http.HttpEntity;
import us.codecraft.webmagic.Page;

import java.awt.image.BufferedImage;

/**
 * Created by Alan on 14-3-13.
 */
public class ImagePage extends Page {

    private BufferedImage image;

    public ImagePage() {
        super();
    }

    public ImagePage(BufferedImage image) {
        super();
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
