package com.eminimal.backend.utils;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.users.FullAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileService {
    private static final String ACCESS_TOKEN = "sl.BShgWz0Kc4H9eKFUghqND8TeWSGCZXfaNTQdTw0HtzkK1Fh1J7KH9k6V_Ti5X0MCdpZb4hX5TvJw9UsdbuHJQiKGScqQ6n_XNCjYOoYPs280Br7I84q6F6cvn3G6rUZp0-5WiVLFTi8";
    private static final String pathDropbox = "/upload/images/";


    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    public String upload(File file) throws Exception {
//		Dropbox
        DbxRequestConfig config = DbxRequestConfig.newBuilder("App/eMinimal").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

        // Upload file to Dropbox
        try (InputStream in = new FileInputStream(file.getPath())) {
//            new SimpleDateFormat("ddMMyy-hhmmss.SSS-file.txt")
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());

            logger.error("File name: " + timeStamp);
            String path = "/upload/images/" + timeStamp  + "_" + file.getName();
            FileMetadata metadata = client.files().uploadBuilder(path).uploadAndFinish(in);

            SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(path);
            return sharedLinkMetadata.getUrl();
        } catch (CreateSharedLinkWithSettingsErrorException ex) {
            throw new Exception(ex.errorValue.tag().toString());
        } catch (IOException | DbxException e) {
            throw new Exception(e.getMessage());
        }
    }

}
