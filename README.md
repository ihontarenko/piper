```text

 +-----------------------------------------+
 |   _____  _____  _____  _______  ______  |
 | |_____]   |   |_____] |______ |_____/   |
 | |       __|__ |       |______ |    \_   |
 |                                         |
 +-----------------------------------------+
   -= PIPER - RUN DESCRIBED COMMANDS =-
        -= IVAN HONTARENKO (2022) =-
```

## Piper Runner

### Build bin file
```bash
./piper.sh -b
```

### Execute Script
```bash
./piper.sh run script-name
```

### Specify file
```bash
./piper.sh run script-name -f ./.piper.prd.yaml
```

### About
```bash
./piper.sh about
```

### Show Environment Variables
```bash
./piper.sh env
```

### .piper.yml
```yaml
version: Beta0

#extends:
#  - ".piper/dev-setup.yml"
#
# $ piper run cd-status start-mariadb
#
# ${ppty:hello} - properties
# ${cfg:sub_script_prefix} - configs
# ${env:JAVA_HOME} - envs

config:
  os: linux
  sub_script_prefix: "@"

properties:
  hello: "Hello World!!!"
  youtube: "youtube.com"
  JAVA_BIN: "${env:JAVA_HOME}/bin/java"

scripts:
  all:
    description: "Run All"
    steps:
      - "@java-version"
      - "@ping"
      - "@docker-stats"
  java-version:
    description: "Java Version"
    steps:
      - "${ppty.JAVA_BIN} -version"
  docker-stats:
    description: "Docker Stats (for 10 lines)"
    steps:
      - "docker stats"
    validators:
      - type: "LINE_COUNT"
        parameters:
          threshold: 10
  ping:
    description: "PING Domain!"
    validators:
      - type: "WAIT_FOR"
        parameters:
          unit: SECONDS
          time: 10
    steps:
      - "ping -n 10 ${ARGS[2]}"
```