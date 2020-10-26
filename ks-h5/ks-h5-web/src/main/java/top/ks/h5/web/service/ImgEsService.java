package top.ks.h5.web.service;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import top.ks.h5.web.model.SpidersImage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ImgEsService {

    IndexResponse createIndex(SpidersImage spidersImage) throws IOException;

    UpdateResponse upsertData(SpidersImage spidersImage) throws IOException, ExecutionException, InterruptedException;
}
