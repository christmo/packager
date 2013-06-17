/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.xplook.procesor.dao.IXplookDB;
import com.xplook.procesor.dao.XplookFactoryDAO;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author christmo
 */
public class TestConfig {
/*
    @Test
    public void config() {
        XplookFactoryDAO dao = new XplookFactoryDAO();
        IXplookDB<XplookPacket> db = dao.getDb();
        try {

            URI url = null;
            try {
//                url = new URI("file://C:/Users/christmo/Documents/NetBeansProjects/packager/target/test-classes/");
                url = getClass().getResource("/").toURI();
                System.out.println("" + url);
            } catch (URISyntaxException ex) {
                Logger.getLogger(TestConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
            WatchService watcher = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(url);
            System.out.println("dir:" + dir.toString());
            try {
                WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);

                for (;;) {

                    // wait for key to be signaled

                    try {
                        key = watcher.take();
                    } catch (InterruptedException x) {
                        return;
                    }

                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();


                        // The filename is the
                        // context of the event.
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path filename = ev.context();

                        // Verify that the new
                        //  file is a text file.
                        try {
                            // Resolve the filename against the directory.
                            // If the filename is "test" and the directory is "foo",
                            // the resolved name is "test/foo".
                            Path child = dir.resolve(filename);
                            if (!Files.probeContentType(child).equals("text/plain")) {
                                System.err.format("New file '%s'"
                                        + " is not a plain text file.%n", filename);
                                continue;
                            }
                        } catch (IOException x) {
                            System.err.println(x);
                            continue;
                        }

                        // Email the file to the
                        //  specified email alias.
                        System.out.format("Emailing file %s%n", filename);
                        //Details left to reader....
                    }

                    // Reset the key -- this step is critical if you want to
                    // receive further watch events.  If the key is no longer valid,
                    // the directory is inaccessible so exit the loop.
                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }

            } catch (IOException x) {
                System.err.println(x);
            }
        } catch (IOException ex) {
            Logger.getLogger(TestConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
*/
}
