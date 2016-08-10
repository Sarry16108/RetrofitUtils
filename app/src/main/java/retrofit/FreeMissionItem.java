package retrofit;

import java.util.List;

/**
 * Created by yanghj on 16/8/8.
 */
public class FreeMissionItem {
    private List<ResourceItem> resourceList;

    public static class ResourceItem {
        private String resourceId;
        private String providerId;
        private String nickName;
        private String headUrl;
        private String demandName;

        public static class ResourceElement {
            private String type;
            private String url;
            private String content;
            private String sequence;
            private String answerTime;
            private String answerTimeStamp;
            private String duration;
            private String mediaType;
        }

        private List<ResourceElement> resourceElementList;

        public String getHeadUrl() {
            return headUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public String getDemandName() {
            return demandName;
        }

        public String getResourceId() {
            return resourceId;
        }
    }

    public List<ResourceItem> getResourceList() {
        return resourceList;
    }
}
