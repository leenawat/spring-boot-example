package krungthai.coa.importbatch.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import static krungthai.coa.importbatch.config.SftpConfig.sftpSessionFactory;

@Log4j2
@Service
public class SftpService {

    private SftpRemoteFileTemplate sftpRemoteFileTemplate;

    public void build(Map<String, String> configMap) {
        sftpRemoteFileTemplate = new SftpRemoteFileTemplate(sftpSessionFactory(configMap));
    }

    public boolean isFileExists(Map<String, String> configMap, String remoteFilePath) {
        build(configMap);
        try {
            sftpRemoteFileTemplate.execute(session -> session.list(remoteFilePath));
            log.info("File exists: " + remoteFilePath);
            return true;
        } catch (Exception ex) {
            log.error("Failed to check file existence: " + ex.getMessage());
            return false;
        }
    }

    public boolean downloadFile(Map<String, String> configMap, String remoteFilePath, String localFilePath) {
        build(configMap);
        try {
            OutputStream outputStream = new FileOutputStream(localFilePath);
            sftpRemoteFileTemplate.execute(session -> session.readRaw(remoteFilePath).transferTo(outputStream));
            log.info("File downloaded: " + localFilePath);
            return true;
        } catch (Exception ex) {
            log.error("Failed to download file: " + ex.getMessage());
            return false;
        }
    }

    public boolean moveFile(Map<String, String> configMap, String sourcePath, String destinationPath) {
        build(configMap);
        try {
            sftpRemoteFileTemplate.execute(session -> {
                session.rename(sourcePath, destinationPath);
                return null;
            });
            log.info("File moved from " + sourcePath + " to " + destinationPath);
            return true;
        } catch (Exception ex) {
            log.error("Failed to move file: " + ex.getMessage());
            return false;
        }
    }

    public boolean writeDirectory(Map<String, String> configMap, String remoteDirectoryPath) {
        build(configMap);
        try {
            sftpRemoteFileTemplate.execute(session -> {
                if (!session.exists(remoteDirectoryPath)) {
                    createNestedDirectories(remoteDirectoryPath);
                    log.info("Directory created: " + remoteDirectoryPath);
                } else {
                    log.info("Directory already exists: " + remoteDirectoryPath);
                }
                return null;
            });
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Failed to create directory: " + ex.getMessage());
            return false;
        }
    }

    private void createNestedDirectories(String remoteDir) throws IOException {
        String[] directories = remoteDir.split("/");
        StringBuilder currentPath = new StringBuilder();

        for (String dir : directories) {
            if (!dir.isEmpty()) { // Skip empty parts (in case of leading slashes)
                currentPath.append("/").append(dir);
                // Check if the directory exists
                if (!sftpRemoteFileTemplate.getSession().exists(currentPath.toString())) {
                    // Create the directory
                    sftpRemoteFileTemplate.getSession().mkdir(currentPath.toString());
                    System.out.println("Directory created: " + currentPath);
                }
            }
        }
    }

}
