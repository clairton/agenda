FROM jboss/wildfly:10.0.0.Final
MAINTAINER Clairton Rodrigo Heinzen, clairton.rodrigo@gmail.com

USER root

#RUN yum -y localinstall https://centos7.iuscommunity.org/ius-release.rpm
#RUN yum -y install git2u.x86_64

RUN yum install -y deltarpm sudo

RUN sh $JBOSS_HOME/bin/add-user.sh admin admin --silent

## https://blog.mikesir87.io/2015/12/creating-wildfly-docker-image-with-postgresql/
RUN /bin/sh -c '$JBOSS_HOME/bin/standalone.sh &' && \
  sleep 10 && \
  #$JBOSS_HOME/bin/jboss-cli.sh --user=admin --password=admin -c --commands=="/subsystem=datasources/data-source=AgendaDS:add(jndi-name=java:/jdbc/datasources/AgendaDS, pool-name=AgendaDS, driver-name=h2, connection-url=jdbc:h2:mem:agenda;DB_CLOSE_DELAY=-1)" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command="data-source add --name=AgendaDS --connection-url=jdbc:h2:mem:agenda;DB_CLOSE_DELAY=-1 --jndi-name=java:/jdbc/datasources/AgendaDS --driver-name=h2" && \
  $JBOSS_HOME/bin/jboss-cli.sh --connect --command=:shutdown && \
  rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/*

RUN chown -R jboss $JBOSS_HOME

RUN curl -o /etc/yum.repos.d/epel-apache-maven.repo https://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo
RUN yum -y install apache-maven

RUN ln -s /opt/jboss/wildfly/bin/standalone.sh /usr/bin/wildfly

USER jboss

EXPOSE 8080 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]