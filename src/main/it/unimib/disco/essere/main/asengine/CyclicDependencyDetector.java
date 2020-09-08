package it.unimib.disco.essere.main.asengine;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.unimib.disco.essere.main.asengine.alg.SedgewickWayneDFSCycleDetectionAlg;
import it.unimib.disco.essere.main.asengine.alg.SzwarcfiterLauerSimpleCyclesCSV;
import it.unimib.disco.essere.main.graphmanager.GraphBuilder;
import it.unimib.disco.essere.main.graphmanager.GraphUtils;
import it.unimib.disco.essere.main.graphmanager.PropertyEdge;

public class CyclicDependencyDetector {

    private static final String INTERNAL = "Internal";
    public static final String PACKAGE_CYCLES = "packageCycles";
    public static final String CLASSES_CYCLES = "classesCycles";
    private static final Logger logger = LogManager.getLogger(CyclicDependencyDetector.class);
    public Graph graph;
    private List<Vertex> classes = null;
    private File path = null;

    public CyclicDependencyDetector(Graph graph, File path) {
        this.graph = graph;
        this.classes = GraphUtils.findVerticesByLabel(graph, GraphBuilder.CLASS);
        this.path = path;
    }

    public void detect() {
        detectCyclesGephiInternal();
    }

    /**
     * Detects the project cycles using the gephi algorithm. It analyzes all
     * packages and classes of the system.
     */
    private void detectCyclesGephi() {
        logger.debug("***Start Cycle detection with Gephi algorithm***");
        SedgewickWayneDFSCycleDetectionAlg algoC = new SedgewickWayneDFSCycleDetectionAlg(
                GraphUtils.findVerticesByLabel(graph, GraphBuilder.CLASS),
                GraphUtils.findEdgesByLabel(graph, PropertyEdge.LABEL_CLASS_DEPENDENCY.toString()), graph, path,
                GraphBuilder.CLASS);
        algoC.execute();

        SedgewickWayneDFSCycleDetectionAlg algoP = new SedgewickWayneDFSCycleDetectionAlg(
                GraphUtils.findVerticesByLabel(graph, GraphBuilder.PACKAGE),
                GraphUtils.findEdgesByLabel(graph, GraphBuilder.LABEL_PACKAGE_AFFERENCE.toString()), graph, path,
                GraphBuilder.PACKAGE);

        algoP.execute();

        logger.debug("***End Cycle detection with Gephi algorithm***");
    }

    /**
     * Detect the project cycles using the gephi algorithm. It only analyzes
     * classes and packages internal to the system (not retrieved).
     */
    private void detectCyclesGephiInternal() {
        List<Vertex> classVertices = GraphUtils.filterProperty(
                GraphUtils.findVerticesByLabel(graph, GraphBuilder.CLASS), GraphBuilder.PROPERTY_CLASS_TYPE,
                GraphBuilder.SYSTEM_CLASS);

        List<Vertex> packageVertices = GraphUtils.filterProperty(
                GraphUtils.findVerticesByLabel(graph, GraphBuilder.PACKAGE), GraphBuilder.PROPERTY_PACKAGE_TYPE,
                GraphBuilder.SYSTEM_PACKAGE);

        logger.debug("***Start Internal Class Cycle detection with Gephi algorithm***");
        SedgewickWayneDFSCycleDetectionAlg algoC = new SedgewickWayneDFSCycleDetectionAlg(classVertices,
                GraphUtils.findEdgesByLabel(graph, PropertyEdge.LABEL_CLASS_DEPENDENCY.toString()), graph, path,
                GraphBuilder.CLASS);
        algoC.execute();

        logger.debug("***End Internal Class Cycle detection with Gephi algorithm***");

        logger.debug("***Start Internal Package Cycle detection with Gephi algorithm***");

        SedgewickWayneDFSCycleDetectionAlg algoP = new SedgewickWayneDFSCycleDetectionAlg(packageVertices,
                GraphUtils.findEdgesByLabel(graph, GraphBuilder.LABEL_PACKAGE_AFFERENCE), graph, path,
                GraphBuilder.PACKAGE);

        algoP.execute();

        logger.debug("***End Internal Package Cycle detection with Gephi algorithm***");
    }

    // Maybe move it to CDUtils
    public List<Vertex> getListOfCycleSmells(String vertexType) {
        List<Vertex> allCyclesSmell = GraphUtils.findVerticesByProperty(graph, GraphBuilder.SMELL,
                GraphBuilder.PROPERTY_SMELL_TYPE, GraphBuilder.CYCLIC_DEPENDENCY);
        if (allCyclesSmell != null) {
            return GraphUtils.filterProperty(allCyclesSmell, GraphBuilder.PROPERTY_VERTEX_TYPE, vertexType);
        } else {
            return null;
        }
    }

}
