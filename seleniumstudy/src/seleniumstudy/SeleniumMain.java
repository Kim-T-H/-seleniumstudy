package seleniumstudy;




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//자바 프로젝트: seleniumstudy
//패키지: seleniumstudy
public class SeleniumMain {
	private WebDriver driver;
	private WebElement webElement;
	public SeleniumMain() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/GDJ24/Downloads/chromedriver_win32/chromedriver.exe");
		driver= new ChromeDriver();
	}
	
	public static void main(String[] args) {
		SeleniumMain main =new SeleniumMain();
		main.crawl();
	}
	private void crawl() {
		try {
		driver.get("http://localhost:8080/jspstudy2/model2/member/loginForm.me");
		//By.name("id") : name="id" 인 태그를 선택
		//findElement : 태그를 선택
		webElement =driver.findElement(By.name("id"));
		webElement.sendKeys("admin");
		
		webElement =driver.findElement(By.name("pass"));
		webElement.sendKeys("1234");
		
		webElement =driver.findElement(By.name("f"));	//f: form 태그
		webElement.submit();	//로그인 버튼 클릭.
		Thread.sleep(3000);
		//회원 목록 보기
		//By.xpath() :xml의 각각의 노드를 찾아가는 방법		/:문서 노드	html:루트태그  / :child 노드   //: 자손노드	 
		//a[@href='list.me'] : @ : a태그의 속성중 href 의 값이 list.me 인 a 태그선택
		
		webElement = driver.findElement(By.xpath("html/body//a[@href='list.me']"));
		webElement.click();	//a태그 클릭
		Thread.sleep(3000);
		driver.get("http://localhost:8080/jspstudy2/model2/member/list.me");
		Document doc=Jsoup.parse(driver.getPageSource());
		Elements div = doc.select("table");
		for(Element e : div) {
			for(Element tr: e.select("tr")) {
				Elements tds =tr.select("td");
				if(tds.size() >0) {
					System.out.println(tds.get(2).html());
				}
			}
		}
		Thread.sleep(5000);
	}catch(Exception e) {e.printStackTrace();}
	finally {driver.close();}
}
}
