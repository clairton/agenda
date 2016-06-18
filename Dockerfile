FROM jboss/wildfly:10.0.0.Final
MAINTAINER Clairton Rodrigo Heinzen, clairton.rodrigo@gmail.com

USER root

ENV container=docker

RUN yum install -y initscripts

RUN (cd /lib/systemd/system/sysinit.target.wants/; for i in *; do [ $i == systemd-tmpfiles-setup.service ] || rm -f $i; done); \
rm -f /lib/systemd/system/multi-user.target.wants/*;\
rm -f /etc/systemd/system/*.wants/*;\
rm -f /lib/systemd/system/local-fs.target.wants/*; \
rm -f /lib/systemd/system/sockets.target.wants/*udev*; \
rm -f /lib/systemd/system/sockets.target.wants/*initctl*; \
rm -f /lib/systemd/system/basic.target.wants/*;\
rm -f /lib/systemd/system/anaconda.target.wants/*;

#RUN yum -y update && yum clean all
#RUN yum install -y wget

#RUN yum -y localinstall https://centos7.iuscommunity.org/ius-release.rpm
#RUN yum -y install git2u.x86_64

#RUN yum install -y deltarpm sudo

RUN useradd -s /usr/sbin/nologin wildfly
RUN chown -R wildfly /opt/jboss/wildfly
RUN mkdir /etc/wildfly
RUN cp /opt/jboss/wildfly/docs/contrib/scripts/systemd/wildfly.conf /etc/wildfly/
RUN echo -e "\n# The address to bind management to\\nWILDFLY_MANAGEMENT_BIND=0.0.0.0" >> /etc/wildfly/wildfly.conf
RUN cp /opt/jboss/wildfly/docs/contrib/scripts/systemd/wildfly.service /etc/systemd/system/
RUN sed -i "s/^EnvironmentFile=-/EnvironmentFile=/" /etc/systemd/system/wildfly.service
RUN sed -i '/\\$WILDFLY_BIND/  s/$/ \\$WILDFLY_MANAGEMENT_BIND/' /etc/systemd/system/wildfly.service
#criado com usuário root para dar certo
RUN sed -i "s/^User=wildfly$/User=wildfly/" /etc/systemd/system/wildfly.service
RUN cp /opt/jboss/wildfly/docs/contrib/scripts/systemd/launch.sh /opt/jboss/wildfly/bin/
RUN chmod +x /opt/jboss/wildfly/bin/launch.sh
RUN sed -i '/-b \$3/  s/$/ -bmanagement \$4/' /opt/jboss/wildfly/bin/launch.sh
RUN systemctl enable wildfly.service
#RUN systemctl start wildfly.service
#RUN sh /opt/jboss/wildfly/bin/jboss-cli.sh --user=admin --password=admin -c --commands="reload"
RUN sh /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
#RUN sh /opt/jboss/wildfly/bin/jboss-cli.sh --user=admin --password=admin -c --commands="/subsystem=datasources/data-source=AgendaDS:add(jndi-name=java:/jdbc/datasources/AgendaDS, pool-name=AgendaDS, driver-name=h2, connection-url=jdbc:h2:mem:agenda;DB_CLOSE_DELAY=-1)  "
#RUN systemctl restart wildfly.service


#RUN curl -o /etc/yum.repos.d/epel-apache-maven.repo https://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo
#RUN yum -y install apache-maven

USER jboss

EXPOSE 8080:8080
EXPOSE 9990:9990

VOLUME [ "/sys/fs/cgroup" ]

CMD ["/usr/sbin/init", "/bin/bash"]