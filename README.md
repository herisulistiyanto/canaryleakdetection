# canaryleakdetection
Simple Example Code How to use Canary Leak Detector

## Synopsis
Simple implementation using Canary Leak Detector on Android App, which it's will give you notification and show
which part of your code that causing memory leak. In this example code will give you memory leak when async task
get interrupted (app destroyed when async process still not done).

## Libs
1. [Butterknife](https://github.com/JakeWharton/butterknife) for view injection
2. [LeakCanary](https://github.com/square/leakcanary) for Leak detector

## Notes
1. Canary need permission access r/w on your external storage, which it will use to write dumping memory report
2. When you run on device, it will install app called "Leak" on your device who will show you the Leak list from your app
