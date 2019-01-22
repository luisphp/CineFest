package hurtado.luis.ejemplo.cinefest;

import java.util.List;

/**
 * Created by luis.hurtado on 07/05/2018.
 */
public class ViewPagerResponse {


    private List<ImagenesBean> imagenes;

    public List<ImagenesBean> getImagenes() {
        return imagenes;
    }


    public static class ImagenesBean {

        private String id;
        private String url;
        private String is_active;
        private String description;

        public String getId() {
            return id;
        }


        public String getUrl() {
            return url;
        }


        public String getIs_active() {
            return is_active;
        }


        public String getDescription() {
            return description;
        }

    }
}
