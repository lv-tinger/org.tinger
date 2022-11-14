package org.tinger.spring.scan;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Created by tinger on 2022-10-21
 */
@Component
@Import(RepoRegister.class)
public class AnnoScannerImporter {
}