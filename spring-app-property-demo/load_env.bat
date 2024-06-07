@echo off
for /f "delims=" %%x in (.env) do (
    set "line=%%x"
    for /f "tokens=1,* delims==" %%a in ("%%line%%") do (
        setx %%a %%b
    )
)