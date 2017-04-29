/**
 * Jenkinsfile
 * branch1
 */
 
// String git_url = 'git@10.0.0.25:/var/opt/gitlab/git-data/repositories/Phil.Hartman/appworx_dhs_demo.git'
// String git_url = 'git@ec2-52-14-108-19.us-east-2.compute.amazonaws.com:/var/opt/gitlab/git-data/repositories/root/appworx_dhs_demo.git'
// String git_url = 'http://ec2-52-14-108-19.us-east-2.compute.amazonaws.com/Phil.Hartman/appworx_dhs_demo_phil.git'
// String git_url = 'git@ec2-52-14-108-19.us-east-2.compute.amazonaws.com:Phil.Hartman/appworx_dhs_demo_phil.git'
// String git_url = 'git@ec2-54-145-168-114.compute-1.amazonaws.com:Phil.Hartman/appworx_dhs_demo.git' //TODO: not working??
  String git_url = 'https://github.com/jacksonm111-org/appworx_dhs_demo'

def deployIntegration(war) 
{
	 sh "scp '${war}' jenkins@10.0.1.191:/home/ubuntu/jboss-as-7.1.1.Final/standalone/deployments"
}

def deployProduction(war) 
{
	sh "scp '${war}' jenkins@10.0.1.193:/home/ubuntu/jboss-as-7.1.1.Final/standalone/deployments"
}

node('slave-pool-1')
{
    sh 'env > env.txt'
    def s = readFile('env.txt')
	def s2 = s.split("\r?\n")
	for(i=0; i<s2.length; i++)
	{
        println a2[i]
    }

	def mvnHome = tool 'M3'
	GIT_BRANCH="master"
	//sh "echo ${GIT_BRANCH}"
	// Mark the code checkout 'stage'....
	stage('Git Checkout')
	{
		 // Get some code from a git server
		 //sh "ssh-agent bash -c 'ssh-add ~/.ssh/jenkins_key; git clone git@192.168.17.14:root/Demo.git'"
		checkout scm: [$class: 'GitSCM', branches: [[name: "*/${GIT_BRANCH}"]], userRemoteConfigs: [[credentialsId: 'git', url: git_url]]]
	}
	// Mark the code analysis 'stage'....
	stage('Code Analysis')
	{
		 // Analyze the code
		 step([$class: 'FindBugsPublisher', canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: 'findbugsXml.xml', unHealthy: ''])
	}
	// Mark the code build 'stage'....
	stage('Build and Run JUnit Tests')
	{
		 // Run the maven build
		 sh "${mvnHome}/bin/mvn clean compile"
		 sh "${mvnHome}/bin/mvn package"
		 step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
	}       
	//Mark the code integration 'stage'
	stage('Deploying to Integration')
	{
		 deployIntegration "/var/lib/jenkins/jobs/CalculatorDemoPipeline/workspace/target/Calculator.war"
	}

	stage('Selenium Tests')
	{
		 //it takes a sec to deploy frontend and backend
		 //sh 'ping 8.8.8.8 -c 2 -i 5 >NUL'
		 sleep 10
		 //sh "${mvnHome}/bin/mvn verify -Pintegration-test -Dtest.url=http://138.47.200.246:8080/Calculator/ -Dselenium.grid.url=http://192.168.17.20"
		 //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
	} 

  stage( 'input')
	{
		mail bcc: '', body: "${BUILD_URL}", cc: '', from: '', replyTo: '', subject: 'Request deploy to production', to: 'hartmanph@nih.gov'
//		input message: 'Joe?', submitter: 'joe'
		input message: 'Hartmanp?', submitter: 'hartmanp'
	}
	
	// mark the code deployment 'stage'
	stage('Deployment to Production')
	{
			deployProduction "/var/lib/jenkins/jobs/CalculatorDemoPipeline/workspace/target/Calculator.war"
	}

} //end node

