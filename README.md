# kotlin / junit5 crash
* Run `gradle check` and the TestEngine crashes
* The offending class is `nl.soqua.dl.domain.projection.album.AlbumProjection$albumByIdentity$$inlined$let$lambda$lambda$1`
* https://github.com/junit-team/junit5/issues/401
* https://youtrack.jetbrains.com/issue/KT-13133
