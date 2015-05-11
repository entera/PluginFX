# WorkbenchFX

[![](https://img.shields.io/travis/entera/WorkbenchFX/master.svg?label=travis)][Travis CI]
[![](https://img.shields.io/maven-central/v/de.entera/workbenchfx.svg?label=bintray)][Bintray JCenter]
[![](https://img.shields.io/maven-central/v/de.entera/workbenchfx.svg?label=maven)][Maven Central]

[Travis CI]: https://travis-ci.org/entera/WorkbenchFX "Travis CI"
[Bintray JCenter]: https://bintray.com/entera/workbenchfx "Bintray JCenter"
[Maven Central]: https://search.maven.org/#search|ga|1|de.entera "Maven Central"

A workbench user interface for JavaFX. &mdash; https://github.com/entera/WorkbenchFX


## Status

This project was started on **May 09, 2015** and is **not ready for production**, yet. :smiley_cat:


## Features

_TBD._


## Usage

~~~groovy
@GrabResolver("https://jcenter.bintray.com/")
@Grab("org.codehaus.groovyfx:groovyfx:0.4.0")
@Grab("de.entera:workbenchfx:0.1.0")

import groovyx.javafx.GroovyFX
import de.entera.workbenchfx.Workbench

GroovyFX.start {
    stage(title: this.class.simpleName, visible: true) {
        scene(width: 640, height: 480) {
            def workbench = new Workbench()
            workbench.content = stackPane(label("content"))
            workbench.leftViews << label("left first") << label("left second")
            stackPane(workbench)
        }
    }
}
~~~


## Motivation

_TBD._


## Contribute

_TBD._


## License

~~~
Copyright 2015 Benjamin Gudehus <hastebrot@gmail.com>

Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
European Commission - subsequent versions of the EUPL (the "Licence"); You may
not use this work except in compliance with the Licence.

You may obtain a copy of the Licence at:
http://ec.europa.eu/idabc/eupl

Unless required by applicable law or agreed to in writing, software distributed
under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
specific language governing permissions and limitations under the Licence.
~~~
