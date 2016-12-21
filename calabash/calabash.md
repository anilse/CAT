"calabash-android run ~/Desktop/Email.apk -v" to run after installing ruby, gem and calabash-android bundle.

Please google "calabash-android, calabash step definitions, etc." for more information.

We need root to test stock AOSP apks. So, on a rooted device uninstall Email and Exchange2 via deleting from system partition. Then install exchange2 via "adb install Exchange2.apk". Then run calabash.
