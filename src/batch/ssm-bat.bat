@echo off
set Version=0.2.3
title Win SSD Slow Mark
if exist ssm-config.cfg (
    for /f "skip=4" %%l in (ssm-config.cfg) do if not defined SsdSlowMark set SsdSlowMark=%%l
    for /f "skip=7" %%l in (ssm-config.cfg) do if not defined Java set Java=%%l
    for /f "skip=10" %%l in (ssm-config.cfg) do if not defined FileCount set FileCount=%%l
    for /f "skip=13" %%l in (ssm-config.cfg) do if not defined TestType set TestType=%%l
    for /f "skip=16" %%l in (ssm-config.cfg) do if not defined FileSize set FileSize=%%l
    for /f "skip=19" %%l in (ssm-config.cfg) do if not defined BlockSize set BlockSize=%%l
    for /f "skip=22" %%l in (ssm-config.cfg) do if not defined DumpFolder set DumpFolder=%%l
    for /f "skip=25" %%l in (ssm-config.cfg) do if not defined ResultsFolder set ResultsFolder=%%l
    for /f "skip=28" %%l in (ssm-config.cfg) do if not defined ImageWidth set ImageWidth=%%l
    for /f "skip=31" %%l in (ssm-config.cfg) do if not defined ImageHeight set ImageHeight=%%l
    for /f "skip=34" %%l in (ssm-config.cfg) do if not defined ImagePadding set ImagePadding=%%l
) else (
    echo Configuration file "ssm-config.cfg" not found. Download it again in "https://github.com/KaioHSG/WinSsdSlowMark".
    pause > nul
    exit
)
if not exist %SsdSlowMark% (
    echo SSD SlowMark file "%SsdSlowMark%" not found. Download it again in "https://github.com/tools4free/SsdSlowMark".
    pause > nul
    exit
)
if not exist %Java% (
   reg query HKEY_CLASSES_ROOT\Applications\java.exe > nul
   if %ErrorLevel% neq 0 (
      echo Java not fund in "%Java%". Download it again in "https://www.java.com/download".
      pause > nul
      exit
   ) else (
      set Java=java
   )
)
echo Win SSD Slow Mark (v%Version%)
echo.
echo Credits:
echo --------------------------------------
echo Java: tools4free
echo Executable: Kaio HSG
echo --------------------------------------
echo.
echo A = Advanced Mode
set /p FileCount=Maximum size (GB): 
if %FileCount% equ a (goto :AdvancedMode) 
if %FileCount% equ A (goto :AdvancedMode)

:StartSsm
title Win SSD Slow Mark - Testing...
cls
echo Start: %Date% - %Time%
echo.
"%Java%" -jar %SsdSlowMark% fc=%FileCount% test=%TestType% fs=%FileSize% bs=%BlockSize% out=%DumpFolder% in=%DumpFolder% rpt=%ResultsFolder% iw=%ImageWidth% ih=%ImageHeight% ip=%ImagePadding%
title Win SSD Slow Mark - Done!
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

:AdvancedMode
cls
echo Win SSD Slow Mark (v%Version%) - Advanced Mode
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
goto :StartSsm
