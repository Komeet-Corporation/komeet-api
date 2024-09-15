package fr.btssio.komeet.komeetapi.etl.tasklet;

import fr.btssio.komeet.komeetapi.service.PathService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class DeleteTempFileTasklet implements Tasklet {

    private final PathService pathService;

    public DeleteTempFileTasklet(final PathService pathService) {
        this.pathService = pathService;
    }

    @Override
    public RepeatStatus execute(@NotNull StepContribution contribution, @NotNull ChunkContext chunkContext) {

        Optional<File[]> optional = Optional.ofNullable(pathService.getTempPath().toFile().listFiles());
        if (optional.isPresent()) {
            final File[] files = optional.get();
            Arrays.stream(files)
                    .forEach(file -> {
                        if (file.delete()) {
                            log.info("Deleted temp file {}", file.getName());
                        }
                    });
        }

        return RepeatStatus.FINISHED;
    }
}
