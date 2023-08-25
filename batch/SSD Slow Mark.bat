@echo off
set Version=0.2
set SsdSlowMark=ssd-slow-mark.jar
set Java=jre-1.8\java.exe
set FileCount=10000
set TestType=rw
set FileSize=1024
set BlockSize=8192
set DumpFolder=dump
set ResultsFolder=./
set ImageWidth=800
set ImageHeight=600
set ImagePadding=60
title SSD Slow Mark
echo SSD Slow Mark (v%Version%)
echo.
echo Credits:
echo --------------------------------------
echo Java: tools4free
echo Batch: Kaio HSG
echo --------------------------------------
echo.
echo 0 = Advanced Mode
set /p FileCount=Maximum size (GB): 
if %FileCount% neq 0 (
    goto :Start
) else (
    goto :AdvancedMode
)

:AdvancedMode
cls
echo SSD Slow Mark (v%Version%) - Advanced Mode
echo.
echo Credits:
echo --------------------------------------
echo Java: tools4free
echo Batch: Kaio HSG
echo --------------------------------------
echo.
set /p FileCount=File count (10000): 
set /p TestType=Test type (r/w/rw): 
set /p FileSize=File size (1024 MB): 
set /p BlockSize=Block size (8192 KB): 
set /p DumpFolder=Dump folder (dump): 
set /p ResultsFolder=Results folder (./): 
set /p ImageWidth=Image width (800 px): 
set /p ImageHeight=Image height (600 px): 
set /p ImagePadding=Image padding (60 px): 
goto :Start

:Start
title SSD Slow Mark - Testing...
cls
echo Start: %Date% - %Time%
echo.
%Java% -jar %SsdSlowMark% fc=%FileCount% test=%TestType% fs=%FileSize% bs=%BlockSize% out=%DumpFolder% in=%DumpFolder% rpt=%ResultsFolder% iw=%ImageWidth% ih=%ImageHeight% ip=%ImagePadding%
title SSD Slow Mark - Done!
echo.
echo Finish: %Date% - %Time%
echo ======================================
echo Done!
if not exist "%DumpFolder%\file-000001.bin" (
    rmdir "%DumpFolder%" /s /q
    echo Press any key to exit...
    pause > nul
    exit
)
choice /c yn /m "Delete dump folder ('%DumpFolder%')"
if %ErrorLevel% equ 1 (    
    rmdir "%DumpFolder%" /s /q
    exit
)
if %ErrorLevel% equ 2 (
    exit
)
