REM https://docs.oracle.com/en/java/javase/14/docs/specs/man/jpackage.html
jpackage -n Cannon -p cannon-jlink/target/dependency -m de.turnertech.cannon/de.turnertech.cannon.Application -d cannon-jpackage --vendor TurnerTech --icon cannon-jpackage/tt-logo.ico --license-file LICENSE.txt --win-shortcut --win-menu
