/**
 * Jenkinsfile
 *
 */
 
// String git_url = 'git@10.0.0.25:/var/opt/gitlab/git-data/repositories/Phil.Hartman/appworx_dhs_demo.git'
// String git_url = 'git@ec2-52-14-108-19.us-east-2.compute.amazonaws.com:/var/opt/gitlab/git-data/repositories/root/appworx_dhs_demo.git'
// String git_url = 'http://ec2-52-14-108-19.us-east-2.compute.amazonaws.com/Phil.Hartman/appworx_dhs_demo_phil.git'
// String git_url = 'git@ec2-52-14-108-19.us-east-2.compute.amazonaws.com:Phil.Hartman/appworx_dhs_demo_phil.git'
// String git_url = 'git@ec2-54-145-168-114.compute-1.amazonaws.com:Phil.Hartman/appworx_dhs_demo.git' //TODO: not working??
//String git_url = 'https://github.com/jacksonm111-org/appworx_dhs_demo'

pipeline {
  agent { label 'label-intg' } /*run on slave by default*/
  
  environment {
    git_url = 'git@github.com:jacksonm111-org/appworx_dhs_demo'
    LABEL_UPPER='label-prod'
    LABEL_LOWER='label-intg'
    LABEL_MASTER='master'
    CREDS="aaf5671dd2b237490fe16bea20d552fefc755a0c"
    GIT_BRANCH="master"
    mvnHome = tool ('M3')
  }

  stages {
    /* run on master */
    /* dump env */
    stage ('dump env') 
    {
      agent { label "master" }
      steps {
        /* dump env */
        sh 'env > env.txt'
        script {
          s = readFile('env.txt').split("\r?\n")
          for(i=0; i <s.length; i++) {
//            println s[i]
          }
        }
        /* clean workspace */
        step([$class: 'WsCleanup'])
      }
    }

    /* run on master */
    /* Mark the code checkout 'stage'.... */
    stage('Git Checkout')
    {
      agent { label "master" }
      steps {
        sh "echo master"

        /* Get some code from a git server */
        /*sh "ssh-agent bash -c 'ssh-add ~/.ssh/jenkins_key; git clone git@192.168.17.14:root/Demo.git'"*/
        checkout scm: [$class: 'GitSCM', branches: [[name: "*/${env.GIT_BRANCH}"]], userRemoteConfigs: [[credentialsId: "${env.CREDS}", url: "${env.git_url}"]]]

        stash 's1'
      }
    }

    /* run on slave */
    /* Mark the code analysis 'stage'.... */
    stage('Analyze Code')
    {
      steps {
        unstash 's1'
        /* Analyze the code */
        step([$class: 'FindBugsPublisher', canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: 'findbugsXml.xml', unHealthy: ''])
      }
    }

    /* run on slave */
    /* Mark the code build 'stage'.... */
    stage('Build and Run JUnit Tests')
    {
      steps {
        /* Run the maven build */
        sh "${mvnHome}/bin/mvn clean compile"
        sh "${mvnHome}/bin/mvn package"
        step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
      }
    }

    /* run on slave */
    /* Mark the code integration 'stage' */
    stage('Deploy to Integration')
    {
      steps {
        sh "scp 'target/Calculator.war' jenkinsa@ncidt-d013-v.nci.nih.gov:/home/ubuntu/intg/jboss-as-7.1.1.Final/standalone/deployments"
      }
    }

    /* run on slave */
    /* Mark the code integration test 'stage'.... */
    stage('Run Selenium Tests')
    {
      steps {
        //it takes a sec to deploy frontend and backend
        //sh 'ping 8.8.8.8 -c 2 -i 5 >NUL'
        sleep 10
        //sh "${mvnHome}/bin/mvn verify -Pintegration-test -Dtest.url=http://138.47.200.246:8080/Calculator/ -Dselenium.grid.url=http://192.168.17.20"
        //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
      }
    } 

    /* run on master */
    /* mark the code approval notify 'stage' */
    stage( 'Notify approver')
    {
      agent { label "master" }
      steps {
        mail bcc: '', body: "${JOB_URL}", cc: '', from: '', replyTo: '', subject: "Request deploy to production (Build ${BUILD_ID})", to: 'hartmanph@nih.gov'
      }
    }

    /* run on master */
    /* mark the code approval wait 'stage' */
    stage( 'Wait for approval')
    {
      agent { label "master" }
      steps {
        timeout(time: 1, unit: 'MINUTES') {
          input message: 'Deploy to prod?'/*, submitter: 'hartmanp'*//*uncomment once authentication enabled*/
        }
      }
    }

    /* run on slave */
    /* mark the code deployment 'stage' */
    stage('Deployment to Production')
    {
      steps {
        sh "scp 'target/Calculator.war' jenkinsa@ncidt-d013-v.nci.nih.gov:/home/ubuntu/prod/jboss-as-7.1.1.Final/standalone/deployments"
      }
    }
  }
}


