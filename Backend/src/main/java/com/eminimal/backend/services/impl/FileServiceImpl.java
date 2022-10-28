package com.eminimal.backend.services.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.users.FullAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class FileServiceImpl{
    private static final String ACCESS_TOKEN = "sl.BRxn-jTGQFTbp789B9BZs0E1EToN4wMPLpjct6LLkLAqFn0TE97GbJ03Z0ASg6QnuabKLOwx_SqF_DIBZ91Ge5n4MecghhnsgqrSEXdvowVWMlNBtlVTa2zSs9M7yVn86ySk0e7pHhk";
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    
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

            String path = "/upload/images/" + file.getName();
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
