package seleniumstudy;




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//�ڹ� ������Ʈ: seleniumstudy
//��Ű��: seleniumstudy
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
		//By.name("id") : name="id" �� �±׸� ����
		//findElement : �±׸� ����
		webElement =driver.findElement(By.name("id"));
		webElement.sendKeys("admin");
		
		webElement =driver.findElement(By.name("pass"));
		webElement.sendKeys("1234");
		
		webElement =driver.findElement(By.name("f"));	//f: form �±�
		webElement.submit();	//�α��� ��ư Ŭ��.
		Thread.sleep(3000);
		//ȸ�� ��� ����
		//By.xpath() :xml�� ������ ��带 ã�ư��� ���		/:���� ���	html:��Ʈ�±�  / :child ���   //: �ڼճ��	 
		//a[@href='list.me'] : @ : a�±��� �Ӽ��� href �� ���� list.me �� a �±׼���
		
		webElement = driver.findElement(By.xpath("html/body//a[@href='list.me']"));
		webElement.click();	//a�±� Ŭ��
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
