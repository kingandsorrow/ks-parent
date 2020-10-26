package top.ks.h5.web.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import top.ks.h5.web.model.SpidersImage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Slf4j
public class ImgEsServiceImpl implements ImgEsService {

    public final static String SPIDERS_IMAGE_ES = "spiders_image_es";
    public final static String SPIDERS_IMAGE_TYPE = "spiders_image";

    private Client client;

    public ImgEsServiceImpl() throws UnknownHostException {
        initEsClient();
    }

    public void initEsClient() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", "esCluster").build();
        if (client == null) {
            this.client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.20.48.13"), 30021));
        }
    }

    @Override
    public IndexResponse createIndex(SpidersImage spidersImage) throws IOException {
        if (!this.indexExists(SPIDERS_IMAGE_ES)) {
            return client.
                    prepareIndex("spiders_image_es", "spiders_image", spidersImage.getId() + "").setSource(jsonBuilder()
                    .startObject()
                    .field("file_url", spidersImage.getFileUrl())
                    .field("source_url", spidersImage.getSourceUrl())
                    .field("name", spidersImage.getName())
                    .field("name1", spidersImage.getName1())
                    .field("read_count", spidersImage.getReadCount())
                    .field("ip_count", spidersImage.getIpCount())
                    .field("create_time", spidersImage.getCreateTime())
                    .field("update_time", spidersImage.getUpdateTime())
                    .endObject())
                    .execute()
                    .actionGet();
        }
        return null;

    }

    /**
     * 判断指定的索引名是否存在
     *
     * @param indexName 索引名
     * @return 存在：true; 不存在：false;
     */
    public boolean indexExists(String indexName) {
        IndicesExistsResponse response =
                client.admin().indices().exists(
                        new IndicesExistsRequest().indices(new String[]{indexName})).actionGet();
        return response.isExists();
    }

    @Override
    public UpdateResponse upsertData(SpidersImage spidersImage) throws IOException, ExecutionException, InterruptedException {
        IndexRequest indexRequest = new IndexRequest("spiders_image_es", "spiders_image", spidersImage.getId()+"")
                .source(jsonBuilder()
                        .startObject()
                        .field("file_url", spidersImage.getFileUrl())
                        .field("source_url", spidersImage.getSourceUrl())
                        .field("name", spidersImage.getName())
                        .field("name1", spidersImage.getName1())
                        .field("read_count", spidersImage.getReadCount())
                        .field("ip_count", spidersImage.getIpCount())
                        .field("create_time", spidersImage.getCreateTime())
                        .field("update_time", spidersImage.getUpdateTime())
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest("spiders_image_es", "spiders_image", spidersImage.getId()+"")
                .doc(jsonBuilder()
                        .startObject()
                        .field("file_url", spidersImage.getFileUrl())
                        .field("source_url", spidersImage.getSourceUrl())
                        .field("name", spidersImage.getName())
                        .field("name1", spidersImage.getName1())
                        .field("read_count", spidersImage.getReadCount())
                        .field("ip_count", spidersImage.getIpCount())
                        .field("create_time", spidersImage.getCreateTime())
                        .field("update_time", spidersImage.getUpdateTime())
                        .endObject())
                .upsert(indexRequest);
        return client.update(updateRequest).get();
    }
}
