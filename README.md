# Wear Chips not clickable on Horologist PagerScreen 

This repo shows that the compact chip or chip is not clickable on the `second` page of the `PagerScreen` on `horologist` at `0.1.15`

Dependencies:
* horologist 0.1.15
* wear compose 1.1.0-beta
* compose 1.3.0

Android Studio 
```
Android Studio Dolphin | 2021.3.1 Patch 1
Build #AI-213.7172.25.2113.9123335, built on September 30, 2022
Runtime version: 11.0.13+0-b1751.21-8125866 aarch64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
macOS 12.6.1
GC: G1 Young Generation, G1 Old Generation
Memory: 4096M
Cores: 10
Registry:
    external.system.auto.import.disabled=true
    ide.text.editor.with.preview.show.floating.toolbar=false
    ide.instant.shutdown=false
```

Emulator Version: 31.3.13
AVD: Wear OS 3 - Preview ARM 64 v8a System Image API 30, Rev 10
Phyical Dev: TicWatch Pro 3 GPS smartwatch

## How to recreate the issue

Please switch the `contentList` variable between `contentList_working` to `contentList_not_working`
in the `onCreate()` of the `MainActivity.kt` 
```
       /* Please switch between the content list from "contentList_not_working"
        * to "contentList_working" to observe the behaviour, chip is clickable
        * only on the first page, but not the second page.
        */
       val contentList = contentList_not_working
       // val contentList = contentList_working
```

to reproduce that the compact chip/ chip is not clickabe on the `second` screen of the `PagerScreen`.



