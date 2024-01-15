[![Version](https://img.shields.io/badge/version-1.2.4.2-green)](https://github.com/KaioHSG/gui-ssd-slow-mark/releases/latest)
[![Downloads](https://img.shields.io/github/downloads/KaioHSG/gui-ssd-slow-mark/total)](https://github.com/KaioHSG/gui-ssd-slow-mark/releases)
[![All contributors](https://img.shields.io/github/contributors-anon/KaioHSG/gui-ssd-slow-mark)](https://github.com/KaioHSG/gui-ssd-slow-mark/contributors)
[![Results](https://img.shields.io/badge/results-📈-blue)](https://github.com/KaioHSG/gui-ssd-slow-mark/discussions/categories/results)
[![Java version](https://img.shields.io/badge/java_8-392-red)](https://wiki.openjdk.org/display/jdk8u)

# GUI SSD Slow Mark

*Check how slow your SSD is.*

## Why GUI SSD Slow Mark?

After discovering that conventional programs like *CrystalDiskMark* only measure the so-called [SLC cache](https://www.technipages.com/what-is-slc-caching), which normally does not exceed 10 GB. I decided to use SSD SlowMark, and realized that I could create an interface for it and make it easier for everyone. So I created the **GUI SSD Slow Mark**, which is an unofficial version of [*SSD SlowMark*](https://github.com/tools4free/SsdSlowMark).

### Comparison

* Other tools:

[![Other tools](https://github.com/KaioHSG/gui-ssd-slow-mark/assets/96930584/d744945f-465f-4bb0-94cd-0ac8e3d2ec58)](#)

* SSD SlowMark:

[![SSD SlowMark](https://github.com/KaioHSG/gui-ssd-slow-mark/assets/96930584/fb5e4369-8b2a-44bf-8e86-9c3b32cf595a)](#)

## How to test?

**1**. Download the [**GUI SSD Slow Mark**](https://github.com/KaioHSG/gui-ssd-slow-mark/releases/latest).

**2**. Now run `GUI-SSD-Slow-Mark.jar` (you will need [**Java**](https://www.java.com/download)).

[![GUI SSD Slow Mark](https://github.com/KaioHSG/gui-ssd-slow-mark/assets/96930584/fd3c046b-8d4a-4750-bf88-b13bb804a85c)](#)

### You can already use it, but here are some explanations:

<table>
<td>

* `File count` = Count of generated files.
* `File size` = Size of every file.
  * `File count` * `File size` = Total size of all files.
* `Block size` = Minimum amount of reading and writing.

* `Dump folder` = Folder where the files generated for the test are located. You can change the disk to be tested by changing to a valid path within it.
* `Results folder` = Test results folder. It must be kept in the program directory.

* `Images` = Configuration for generated images.

* `Test type` = Indicates what type of test (writing/reading/writing and reading). For reading testing, files must be previously generated.

</td>
</table>

### List of terminal commands:

<table>
<td>

* `fc` = Number of files (required to use without GUI).
* `fs` = Size of files (MB).
* `bs` = Block size (KB).

* `dump` = Dump directory ("path").
* `res` = Results folder ("path").

</td>
<td>

* `iw` = Width of images (px).
* `ih` = Height of images (px).
* `ip` = Padding of images (px).

* `test` = Read (r) / write (w) / read and write (rw).

* `gui` = Log GUI (true / false).

</td>
</table>

**Exemple:**

``` console
java -jar GUI-SSD-Slow-Mark.jar fc=40 fs=512
```

## What's in report?

In generated report you will find:

* Averaged summary, where most important things are:

  * Portion of data written at highest speed - This typically correspond to size of SLC cache available currently.

  Note: some drives may perform either constantly bad, this means it does not have SLC cache. Or constantly good, this means either it has quick memory like [3D XPoint](https://en.wikipedia.org/wiki/3D_XPoint) or you did not yet rech end of SLC cache area which in same cases may take 20-30% of disk drive.

  * Max read speed - How quickly your programs start depends on that.

  * Most typical write speed - How quickly you can fill your disk depends on that.

  [![Report Averages](https://github.com/KaioHSG/gui-ssd-slow-mark/assets/96930584/cde456f2-c7bf-4e09-90cd-bf919c3eee66)](#)

* Detailed chart of read and write test:

  [![Report Chart](https://github.com/KaioHSG/gui-ssd-slow-mark/assets/96930584/eb944387-3238-46ed-ada2-d799fbda70d4)](#)

* Raw metrics are collected into CSV files. You may use them to build aggregated data for multiple drives in Excel or similar tool.

## User results

You can post your results and discuss them on the [results](https://github.com/KaioHSG/gui-ssd-slow-mark/discussions/categories/results) page.

## Problems & bugs

You can post your problems and bugs on the [problems & bugs](https://github.com/KaioHSG/gui-ssd-slow-mark/discussions/categories/problems-bugs) page. 
(Note: the program has been forked, so I don't have full knowledge to solve more complex problems, but I should try to fix any inconsistencies.)

## Credits

This is all a fork of [SSD SlowMark](https://github.com/tools4free/SsdSlowMark) created by [tools4free](https://github.com/tools4free). I just made a interface.

**SSD SlowMark** website: https://tools4free.github.io/ssd-slow-mark
