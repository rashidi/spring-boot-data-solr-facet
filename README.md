# Solr Facet With Spring Boot Data Solr
With less code there will be less mistake

## Background
While exploring Solr I discovered `facet` which similar to SQL `DISTINCT`. One thing lead to another I learnt that `spring-data-solr` supports `facet` with `@Facet` annotation.

## Example
We would like to get distinct of location from the following table:

name         | location
-------------|-------------
Bruce Wayne  | Gotham City
Dick Grayson | Gotham City
Clark Kent   | Metropolis
Conner Kent  | Metropolis
Barry Allen  | Central City

This can be achieved with `@Facet`. Here is an example from [PersonRepository.java](src/main/java/my/zin/rashidi/data/solr/domain/PersonRepository.java):

```
@Query(value = "*:*")
@Facet(fields = { Person.FIELD_LOCATION })
FacetPage<Person> findAllAndFacetByLocation(Pageable pageable);
```

The client will be call `findAllAndFacetByLocation`. Taken from [PersonRepositoryTests.java](src/test/java/my/zin/rashidi/data/solr/domain/PersonRepositoryTests.java):

```
Collection<String> locations = new ArrayList<>();

Pageable pageable = new PageRequest(0, 10);
FacetPage<Person> results = repository.findAllAndFacetByLocation(pageable);

results.getFacetResultPage("location").getContent().forEach(
    content -> locations.add(content.getValue())
);
```

As a result `locations` will have three values; _Gotham City, Metropolis, Central City_.

## License
```
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org/>
```
