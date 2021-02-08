# db-switcher

This is a simple demo of annotation for the dynamic data source.
Simply 2 steps:

1. Implement the Configuration of the dynamic data source. The key part is to use
```AbstractRoutingDataSource.determineCurrentLookupKey``` which is used to look up the key of data source.

2. With AOP to implement the ```DS``` annotation, 
which can be used to methods in controller and service layers.

