# fvm

## Windows

Install: choco install fvm

List all available Flutter versions: fvm releases

List all installed Flutter versions: fvm list

Set flutter installation directory: Add FVM_CACHE_PATH to `environment variables`

Install flutter: fvm install version_name

Remove flutter: fvm remove version_name

Global: fvm global version_name

Use: fvm use version_name

Use flutter command: fvm flutter command_name

Use flutter with verbose output in windows: fvm flutter run -d windows -v

Use flutter with verbose output in android: fvm flutter run -d emulator-xxxx -v (emulator-xxxx is the id of the android emulator)

Use flutter with argument in windows: fvm flutter run -d windows --dart-entrypoint-args --argument (Defining arguments using the args package)

## Linux

Install: curl -fsSL <https://fvm.app/install.sh> | bash
