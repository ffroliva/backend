package br.com.ffroliva.portfolio.service;

import br.com.ffroliva.portfolio.config.properties.ApplicationProperties;
import br.com.ffroliva.portfolio.exception.FileStorageException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
class FileStorageServiceTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Mock
    private ApplicationProperties.FileStorageProperties fileStorageProperties;


    @Test
    void store_a_valid_file() {
        MockMultipartFile file = new MockMultipartFile(
                "data",
                "filename.txt",
                "text/plain",
                "some xml".getBytes());
        final String fileName = fileStorageService.storeFile(file);
        Assertions.assertEquals(file.getOriginalFilename(), fileName);
    }

    @Test
    void try_to_store_a_file_with_an_invalid_name() {
        MockMultipartFile file = new MockMultipartFile(
                "data",
                ".....",
                "text/plain",
                "some xml".getBytes());

        Assertions.assertThrows(FileStorageException.class, () -> fileStorageService.storeFile(file));
    }

    //TODO: find a path that throws exception!
    @Disabled
    @Test
    void throws_file_storate_exception_on_create_directories(){
        Mockito.when(fileStorageProperties.getUploadDir()).thenReturn("\"");
        Assertions.assertThrows(FileStorageException.class, () -> new FileStorageService(fileStorageProperties));
    }

}
