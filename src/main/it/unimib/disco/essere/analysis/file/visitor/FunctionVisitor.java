package it.unimib.disco.essere.analysis.file.visitor;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import com.google.common.base.Function;

/**
 * Created by IntelliJ IDEA.
 * User: bbejeck
 * Date: 1/28/12
 * Time: 9:07 PM
 */

public class FunctionVisitor extends SimpleFileVisitor<Path> {
    
    Function<Path,FileVisitResult> pathFunction;

    public FunctionVisitor(Function<Path, FileVisitResult> pathFunction) {
        this.pathFunction = pathFunction;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        return pathFunction.apply(file);
    }
}
