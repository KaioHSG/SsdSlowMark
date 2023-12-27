# GUI SSD Slow Mark

[![Downloads](https://img.shields.io/github/downloads/KaioHSG/gui-ssd-slow-mark/total)](https://github.com/KaioHSG/gui-ssd-slow-mark/releases)
[![All contributors](https://img.shields.io/github/contributors-anon/KaioHSG/gui-ssd-slow-mark)](https://github.com/KaioHSG/gui-ssd-slow-mark/contributors)
[![Stars](https://img.shields.io/github/stars/KaioHSG/gui-ssd-slow-mark)](https://github.com/KaioHSG/gui-ssd-slow-mark/stargazers)
[![Java version](https://img.shields.io/badge/java-21-red)](https://www.java.com)

*Check how slow your SSD is.*

## Why GUI SSD Slow Mark?

After discovering that conventional programs like *CrystalDiskMark* only measure the so-called [SLC cache](https://www.technipages.com/what-is-slc-caching), which normally does not exceed 10 GB. I decided to use SSD SlowMark, and realized that I could create an interface for it and make it easier for everyone. So I created the **GUI SSD Slow Mark**, which is an unofficial version of [*SSD SlowMark*](https://github.com/tools4free/SsdSlowMark).

![GUI SSD Slow Mark](https://github.com/KaioHSG/gui-ssd-slow-mark/assets/96930584/fd3c046b-8d4a-4750-bf88-b13bb804a85c)

### Comparison

Other tools:

![Other tools](https://raw.githubusercontent.com/tools4free/tools4free.github.io/master/ssd-slow-mark/images/same-from-other-tools.png)

SSD SlowMark:

![SSD SlowMark](https://raw.githubusercontent.com/tools4free/tools4free.github.io/master/ssd-slow-mark/images/sample-write-chart.png)

## How to test?

**1**. Download a version of [**GUI SSD Slow Mark**](https://github.com/kaiohsg/gui-ssd-slow-mark/releases/latest) (`GUI-SSD-Slow-Mark.exe` is best).

**2**. If you downloaded `GUI-SSD-Slow-Mark.exe` just run it. If you downloaded `GUI-SSD-Slow-Mark.jar` you will need [**Java**](https://www.java.com/download).

### You can already use it, but here are some explanations:

* `File count` = Count of generated files.
* `File size` = Size of every file.
  * `File count` * `File size` = Total size of all files.
* `Block size` = Minimum amount of reading and writing.

* `Dump folder` = Folder where the files generated for the test are located. You can change the disk to be tested by changing to a valid path within it.
* `Results folder` = Test results folder. It must be kept in the program directory.

* `Images` = Configuration for generated images.

* `Test type` = Indicates what type of test (writing/reading/writing and reading). For reading testing, files must be previously generated.

## What's in report?

In generated report you will find:

* Averaged summary, where most important things are:

  * Portion of data written at highest speed - This typically correspond to size of SLC cache available currently.

  Note: some drives may perform either constantly bad, this means it does not have SLC cache. Or constantly good, this means either it has quick memory like [3D XPoint](https://en.wikipedia.org/wiki/3D_XPoint) or you did not yet rech end of SLC cache area which in same cases may take 20-30% of disk drive.

  * Max read speed - How quickly your programs start depends on that.

  * Most typical write speed - How quickly you can fill your disk depends on that.

  ![Report Averages](https://raw.githubusercontent.com/tools4free/tools4free.github.io/master/ssd-slow-mark/images/report-averages.png)

* Detailed chart of read and write test:

  ![Report Chart](https://raw.githubusercontent.com/tools4free/tools4free.github.io/master/ssd-slow-mark/images/report-chart.png)

* Raw metrics are collected into CSV files. You may use them to build aggregated data for multiple drives in Excel or similar tool.

## Credits

This is all a fork of [SSD SlowMark](https://github.com/tools4free/SsdSlowMark) created by [tools4free](https://github.com/tools4free). I just made a interface.

**SSD SlowMark** website: https://tools4free.github.io/ssd-slow-mark
