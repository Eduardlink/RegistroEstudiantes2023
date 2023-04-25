@echo off
msiexec /i Setup.msi /qn /l*v c:\msi.trace
@pause

REM this batch file will perform a silent install