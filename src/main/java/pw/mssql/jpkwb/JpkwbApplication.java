package pw.mssql.jpkwb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pw.mssql.jpkwb.service.JpkWbService;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JpkwbApplication implements CommandLineRunner {

    private final JpkWbService jpkWbService;

    public static void main(String[] args) {
        log.info("JPKWB application started");
        SpringApplication.run(JpkwbApplication.class, args);
        log.info("JPKWB application finished");
    }

    @Override
    public void run(String... args) throws Exception {
        jpkWbService.processJpkWb();
    }
}
