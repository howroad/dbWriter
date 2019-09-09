@echo off
setlocal enabledelayedexpansion
del PATCH.PDC
del OTHERS.PDC


echo "提取SQL文件开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
set s=%cd%\
for /r %%i in (*.TAB,*.PDC) do (
    set b=%%i
    set r=!b:%s%=!
    @echo @!r!;>>PATCH.PDC
    @echo %%~ni%%~xi    %%~ti    %%~zi B
)
echo "提取SQL文件结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*"


echo "提取其它文件开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
for /r %%i in (*.trg,*.prc,*.fnc) do (
    set b=%%i
    set r=!b:%s%=!
    @echo @!r!;>>OTHERS.PDC
    @echo %%~ni%%~xi    %%~ti    %%~zi B
)
echo "提取其它文件结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"

pause