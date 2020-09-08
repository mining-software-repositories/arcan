## Project Name
arcan

## Synopsis
A software analysis tool to detect Software Architectural Smells.

## References
Arcelli Fontana, Francesca, Ilaria Pigazzini, Riccardo Roveda, and Marco Zanoni. 
“Automatic Detection of Instability Architectural Smells.” 
In *Proceedings of the 32nd International Conference on Software Maintenance and Evolution (Icsme 2016)*. 
Raleigh, North Carolina, USA: IEEE.
[pdf](http://essere.disco.unimib.it/wiki/_media/publications/arcellifontana2016c-icsme-era2016.pdf)

Arcelli Fontana, Francesca, Ilaria Pigazzini, Riccardo Roveda, Damian Andriew Tamburri, Marco Zanoni and Di Nitto Elisabetta.
“Arcan: a Tool for Architectural Smells Detection.” 
*International Conference On Software Architecture (ICSA 2017)*. 
Gothenburg, Sweden, April 3-7, IEEE.
[pdf](http://essere.disco.unimib.it/wiki/_media/publications/pid4705339.pdf)

## Developed With
* JDK 1.8
* TinkerPop3 http://tinkerpop.apache.org/docs/3.1.1-incubating/reference/
* BCEL 6.2 https://commons.apache.org/proper/commons-bcel/manual.html
* Neo4J http://neo4j.com/

## Dependency Graph
Arcan generates a dependency graph after the bytecode reading. The directed graph 
is generated throuh Tinkerpop [link], a graph computing library and can be stored 
in a neo4j database.
Arcan supports two types of graph: Tinkerpop graph and Neo4j graph. 
The first is in-memory and can not be stored in a graph database, the second can be stored in a neo4j graph database.
In order to obtain instances of such graphs, use: 
```java
InterfaceModel model = new InterfaceModel();

// return tinkerpop graph
Graph tinkergraph = model.runGraphBuildingTinkerpop();

// return neo4j graph
Graph neo4jgraph = model.runGraphBuildingNeo4j();
```

### Nodes
Tinkerpop nodes can be labelled and can store String values thanks to *properties*.
Nodes label, properties and property values are listed in **PropertyVertex.java**. 
It is an enum which allows to retrieve the string values used to create the graph schema.

### Edges
Tinkerpop edges can be labelled and can store String values thanks to *properties*.
Edges label are listed in **LabelEdge.java**. 
It is an enum which allows to retrieve the string values used to create the graph schema.

## How to...
This section contains brief instructions to manage the dependency graph and extend Arcan.

### Navigate the dependency graph
In general, to start a graph navigation ("traversal"):
```java
graph.traversal()
```
Tinkerpop allows to traverse nodes and edges separately. See the tinkerpop documentation for more information.


Example:

```java
// get the graph which represents the project under analysis
Graph graph = model.getGraph();

// navigate the graph and return all nodes (vertices) which has property "FAN_IN" set to 5
List<Vertex> classes = graph.traversal().V().hasLabel(PropertyVertex.CLASS.toString().has(PropertyVertex.PROPERTY_FAN_IN, 5).toList();
```

**NOTICE!**
Class **GraphUtils.java** in the graphmanager package already offers a set of methods to execute
common queries on the graph. Check it out!

### Add node to the graph
Example:

```java
// add node
Vertex newSmellVertex = graph.addVertex(T.label, PropertyVertex.SMELL.toString(),
                PropertyVertex.PROPERTY_NAME.toString(), "newSmellVertexName");
                
// add property to the newly created node
newSmellVertex.property(PropertyVertex.SMELL_TYPE.toString(), "newSmellType")
```

**NOTICE!** 
Usually the creation of a new type of SMELL is implemented in a method in the GraphUtils.java class        

### Add edge to the graph

example:

```java
// create a new edge from the new smell node to a class node which is affected by the smell.
Edge newSmellEdge = newSmellVertex.addEdge(LabelEdge.SMELL_AFFECTED.toString(), classAffectedNode);
```

# Architectural smell detection

New detector should:

* offer a public detect() method;
* store the result of the detection (when possible) in the dependency graph as a node with label smell;
* offer a printer to return the results in .csv format.


## Contributors
* Ilaria Pigazzini i.pigazzini@campus.unimib.it
* Riccardo Roveda r.roveda@campus.unimib.it