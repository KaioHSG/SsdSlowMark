::[Bat To Exe Converter]
::
::YAwzoRdxOk+EWAjk
::fBw5plQjdCyDJGyX8VAjFDBVRQu+D2SvFLYZ1O7y4++Unl4OWa82e4DVlL2NL4A=
::YAwzuBVtJxjWCl3EqQJgSA==
::ZR4luwNxJguZRRnk
::Yhs/ulQjdF+5
::cxAkpRVqdFKZSDk=
::cBs/ulQjdF+5
::ZR41oxFsdFKZSDk=
::eBoioBt6dFKZSDk=
::cRo6pxp7LAbNWATEpCI=
::egkzugNsPRvcWATEpCI=
::dAsiuh18IRvcCxnZtBJQ
::cRYluBh/LU+EWAnk
::YxY4rhs+aU+IeA==
::cxY6rQJ7JhzQF1fEqQJgZksaHkrSXA==
::ZQ05rAF9IBncCkqN+0xwdVsEAlbi
::ZQ05rAF9IAHYFVzEqQIDGz8UfwiNKyuXB6cXiA==
::eg0/rx1wNQPfEVWB+kM9LVsJDBeRMSa4B6FS6unvjw==
::fBEirQZwNQPfEVWB+kM9LVsJDBeRMQs=
::cRolqwZ3JBvQF1fEqQITIB5XR0SKM3z6FbkT/6ji4P+V4n4ucKI2a8G7
::dhA7uBVwLU+EWE2L+04jfB1GSQHi
::YQ03rBFzNR3SWATElA==
::dhAmsQZ3MwfNWATE4E0/JAgAShaHOSf6LbQV56jT3M3n
::ZQ0/vhVqMQ3MEVWAtB9wSA==
::Zg8zqx1/OA3MEVWAtB9wSA==
::dhA7pRFwIByZRRnk
::Zh4grVQjdCyDJGyX8VAjFDBVRQu+D2SvFLYZ1Nvo69mLrVowVfA0WprS1rCyE9M3qnboeoFt+3tIk4UJFB44
::YB416Ek+ZG8=
::
::
::978f952a14a936cc963da21a135fa983
@echo off
set Version=0.2

:SsmConfig
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
    echo Configuration file "ssm-config.cfg" not found. Download it again in "https://github.com/KaioHSG/SsdSlowMark/releases".
    pause > nul
    exit
)

if not exist %SsdSlowMark% (
    echo SSD Slow Mark file "%SsdSlowMark%" not found. Download it again in "https://github.com/KaioHSG/SsdSlowMark/releases".
    pause > nul
    exit
)
if not exist %Java% (
    echo Java file "%Java%" not found. Download it again in "https://github.com/KaioHSG/SsdSlowMark/releases".
    pause > nul
    exit
)
title SSD Slow Mark
echo SSD Slow Mark (v%Version%)
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
goto :StartSsm