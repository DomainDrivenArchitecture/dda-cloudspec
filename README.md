# dda-cloudspec

# rationale
After the test driven validation of servers has been accomplished (e.g. with rspec & ServerSpec / Goss ), what is missing is the validation of the whole cloud - a CloudSpec.

##Possible Areas of Application:
* Are DNS-entries set correctly
* Are the needed networks accessible
* Are the ELBs connected to the correct Autoscaling-Groups
* Are the permissions set correctly


How would it be if we could employ a kind of probe somewhere in the cloud and this probe could then test the network connections and similar areas?

##What tools are already available for this?

There is mostly AWS Spec - <https://github.com/k1LoW/awspec>
AWS Spec is testing on a low-level, and might be suitable in the cases where terraform can not be trusted.


##What should CloudSpec be able to test?
* DNS resolution: nslookup
* network access: netcat
* ports: nmap (paranoid mode)
* possibly http: curl

##At which point could a CloudSpec-test be executed?

* At the time of deployment: Here the deployment-target can be used as a probe.
* As a singular standalone-test: A small instance can be instantiated as its own probe and on it a few tests can be run. The instance is then destroyed after the test.
* As a permanent monitoring: A small instance can be instantiated as its own probe and on it a few tests are run cyclical. Possible the telegraf server agent could be added to the probe.


##How could one realize such a test?

We use a ServerTest-tool, which executes nmap, netcat and nslookup.
The different time points of execution additionally need different modes:

1. Deployment-Time: the tool needs to be able to execute remote tests (only nmap, netcat and nslookup are installed on the target)
2. Standalone-Test: we have a ami / docker that contains nmap/netcat/nslookup and the servertest-tool. The servertest is run local.
3. Monitoring: we have a ami / docker that contains nmap/netcat/nslookup, the servertest-tool and telegraf. The servertest is run local. 

##Basis
<https://github.com/DomainDrivenArchitecture/dda-serverspec-crate>
Can test local and remote. In the remote- case only the tools on the target are required. The test runtime environment needs a jdk.
The tool can be started in this way:
java -jar dda-serverspec-crate.jar --spec specfile.yaml
Also thinkable: serverspec & rake

##See also:

-> zips oder binaries in github hinterlegen

$ nc wkewkjdwjdw.de 80
nc: getaddrinfo: Name or service not known

$ echo $?
1

-T paranoid|sneaky|polite|normal|aggressive|insane (Set a timing template)

$ nmap -oX - -p 80-85 heise.de
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE nmaprun>
<?xml-stylesheet href="file:///usr/bin/../share/nmap/nmap.xsl(file:///usr/share/nmap/nmap.xsl)" type="text/xsl"?>
<!-- Nmap 7.01 scan initiated Wed Aug 9 11:05:02 2017 as: nmap -oX - -p 80-85 heise.de -->
<nmaprun scanner="nmap" args="nmap -oX - -p 80-85 heise.de" start="1502269502" startstr="Wed Aug 9 11:05:02 2017" version="7.01" xmloutputversion="1.04">
<scaninfo type="connect" protocol="tcp" numservices="6" services="80-85"/>
<verbose level="0"/>
<debugging level="0"/>
<host starttime="1502269502" endtime="1502269502"><status state="up" reason="syn-ack" reason_ttl="0"/>
<address addr="193.99.144.80" addrtype="ipv4"/>
<hostnames>
<hostname name="heise.de" type="user"/>
<hostname name="redirector.heise.de" type="PTR"/>
</hostnames>
<ports><port protocol="tcp" portid="80"><state state="open" reason="syn-ack" reason_ttl="0"/><service name="http" method="table" conf="3"/></port>
<port protocol="tcp" portid="81"><state state="closed" reason="conn-refused" reason_ttl="0"/><service name="hosts2-ns" method="table" conf="3"/></port>
<port protocol="tcp" portid="82"><state state="closed" reason="conn-refused" reason_ttl="0"/><service name="xfer" method="table" conf="3"/></port>
<port protocol="tcp" portid="83"><state state="closed" reason="conn-refused" reason_ttl="0"/><service name="mit-ml-dev" method="table" conf="3"/></port>
<port protocol="tcp" portid="84"><state state="closed" reason="conn-refused" reason_ttl="0"/><service name="ctf" method="table" conf="3"/></port>
<port protocol="tcp" portid="85"><state state="closed" reason="conn-refused" reason_ttl="0"/><service name="mit-ml-dev" method="table" conf="3"/></port>
</ports>
<times srtt="11586" rttvar="7922" to="100000"/>
</host>
<runstats><finished time="1502269502" timestr="Wed Aug 9 11:05:02 2017" elapsed="0.08" summary="Nmap done at Wed Aug 9 11:05:02 2017; 1 IP address (1 ho
st up) scanned in 0.08 seconds" exit="success"/><hosts up="1" down="0" total="1"/>
</runstats>
</nmaprun>
