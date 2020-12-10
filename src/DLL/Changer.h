#pragma once

// main dll header file for my written code
#if DESKTOP_EXPORTS
#define DESKTOP_API __declspec(dllexport)
#else
#define DESKTOP_API __declspec(dllexport)
#endif // DESKTOP_EXPORTS


extern "C" DESKTOP_API void Java_dllFuncs_change_wallpaper();