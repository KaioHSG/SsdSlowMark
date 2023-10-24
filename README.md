# Win SSD Slow Mark 

*Check how slow your SSD is.*

## Why Win SSD Slow Mark?

After discovering that conventional programs like *CrystalDiskMark* only measure the so-called [SLC cache](https://www.technipages.com/what-is-slc-caching), which is normally no larger than 10 GB. I decided to use *SSD SlowMark*, but realized that it was quite complicated for a Windows layman. So I created **Win SSD Slow Mark** which is an unofficial build of [*SSD SlowMark*](https://github.com/tools4free/SsdSlowMark).

## How to test?

**1**. Download a version of [**Win SSD Slow Mark**](https://github.com/KaioHSG/WinSsdSlowMark/releases/latest) (`Win-SSD-Slow-Mark.exe.zip` is best).

**2**. If you downloaded `Win SSD Slow Mark.exe` just run it. If you downloaded `Win SSD Slow Mark.bat` you will need [**Java**](https://www.java.com/download) and [**SSD SlowMark**](https://github.com/tools4free/SsdSlowMark/releases/latest).

## What's in report?

In generated report you will find:

* Averaged summary, where most important things are:

  * Portion of data written at highest speed - This typically correspond to size of SLC cache available currently.

  Note: some drives may perform either constantly bad, this means it does not have SLC cache. Or constantly good, this means either it has quick memory like [3D XPoint](https://en.wikipedia.org/wiki/3D_XPoint) or you did not yet rech end of SLC cache area which in same cases may take 20-30% of disk drive.

  * Max read speed - How quickly your programs start depends on that.

  * Most typical write speed - How quickly you can fill your disk depends on that.

  ![Report Averages](https://github.com/KaioHSG/WinSsdSlowMark/assets/96930584/ac9be217-62b9-432c-abc1-2bb353e9633d.png)

* Detailed chart of read and write test:

  ![Report Chart](https://github.com/KaioHSG/WinSsdSlowMark/assets/96930584/878df293-b789-48e5-98c8-8dff51e9f97f.png)

* Raw metrics are collected into CSV files. You may use them to build aggregated data for multiple drives in Excel or similar tool.

## Credits

This is all a fork of [SSD SlowMark](https://github.com/tools4free/SsdSlowMark) created by [tools4free](https://github.com/tools4free). I just made a build for Windows.

**SSD SlowMark** website: https://tools4free.github.io/ssd-slow-mark
