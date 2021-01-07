#### สารบัญ

- โครงสร้างเเอพ
- เครื่องมือ
- สรุปการเรียนรู้
---

### โครงสร้างเเอพ
โครงสร้างที่ผมเลือกใช่คือ MVC(Model View Controller) โดยสาม Class หลักเหล่านี้มีหน้าที่ของตัวเองโดยผมเปรียบกับร้านอาหาร
 
 - Model เปรียบเสมือนครัวเเละพ่อครัวโดยเขาจะยุ่งกับข้อมูลโดยตรงรวมทั้งเชื่อมAPIหรือDatabaseเเละยังมีหน้าที่ในการดำเนินการเชิงคำนวณอีกด้วย
 - View เปรียบเสมือนMenuมีหน้าที่ในการนำเสนอเเละตอบสนองกับลูกค้า
 - Controller เปรียบเสมือนเด็กเสิร์ฟมีหน้าที่ในการติดต่อระหว่างviewเเละmodelโดยนำสิ่งที่ลูกค้าอยากได้ไปให้Modelหรือนำสิ่งที่Modelป้อนข้อมูลให้ไปส่งเเก่ลูกค้า
 
 โดยเราจะเห็นได้ว่า Model View Controller เหล่านี้จะมีความสัมพันธ์ต่อกัน
 
 - Model      - Controller
 - Controller - View
 - Controller - Model

ซึ่งความสัมพันธ์นี้ส่วนใหญ่จะเป็นความสัมพันธ์เเบบ Has-A ยกตัวอย่างเช่น Gun Has-A Bullet, A Content Has-A Heading, A Chicken Has-A Egg

 เริ่มด้วยการสร้าง 3 Interface หลักซึ่งมีหน้าที่เหมือนกับเเม่พิม
 
 #### 3 Main Interfaces (โดยผมจะอธิบายหน้าที่ของเเต่ละ method ภายหลังในส่วนนี้จะเป็นการอธิบายโครงสร้างของมันก่อน)
 Abstract Class or Interface 
 
 
java
 public interface IModel {
  
  // empty space for implementation
  
 }
 
 public interface IView {
 
 // empty space for implementation
 
}

public interface IController {

// empty space for implementation

}
 

 
 #### View(Concrete Class)
java
public class MainActivity extends AppCompatActivity implements IView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
    }
    
}

 #### Controller(Concrete Class)
java
public class Controller implements IController{
    //ความสัมพันธ์เเบบ Has-A Controller - View
    static IView view;
    //ความสัมพันธ์เเบบ Has-A Controller - Model
    static IModel model;

    public Controller(IView view) {
        this.view = view;
        this.model = new Model(this);
    }
     

}

 #### Model(Concrete Class)
 
java
 public class Model  implements IModel{
    //Database
    private  List<coin> objects;
    //ความสัมพันธ์เเบบ Has-A Model - Controller
    public  IController controller1;

    public Model(Controller controller) {
                controller1 = controller;
         }
     
}
## View
จากโครงสร้างด้านบนจะสังเกตได้ว่าผมให้MainActivityเป็นclassที่ implement View Interface ซึ่งหมายความว่าผมให้ MainActivity นี้ทำหน้าที่เป็น View นั้นเองคอยนำเสนอให้กับลูกค้า
เเละยังมีหน้าที่รับหรือส่งข้อมูลหรือความต้องการของลูกค้าให้กับControllerอีกด้วย ทีนี้ถึงเวลาลงลึกmethodsที่ผมสร้างขึ้นหรือทางAndroidที่เตรียมให้มา

 
Java
 public class MainActivity extends AppCompatActivity implements IView {
    private RecyclerView recyclerView;
    private IController controller;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // onCreate() คือ method หลักที่ทางActivityให้มาซึ่งถ้าลงลึกไปอีกเราก็จะเจอกับlifecycleของมัน หลายส่วนส่วนของlifecycleนี้จะครอบคุมตั้งเเต่เเอพถูกสร้างขึ้นไปจนถึงเมื่อเราปิดเเอพไป
       // เนื่องจาก Assignment นี้ไม่ได้มีความจำเป็นต้องใช่Stateอื่นเราจึงใช่เเค่ onCreate() เพียงเท่านั้น
       
       //implement(A)
    }
    @Override
    public void ShowDataOnRecycleView() {
    
    //implement(B)
       
    }
}
### implement(A)
ในส่วนนี้สิ่งที่เราต้องทำคือทำการ initlize 2 ส่วนหลักประกอบไปด้วย
- UI
- Class Relationship

#### UI
ฟังชั่นพื้นฐานเลยที่developerทุกต้องรู้คือ findViewById() มันจะbinding กับcomponentต่างๆที่อยู่บนUIไม่ว่าจะเป็นการ binding ปุ่มกด inserting ภาพต่างๆ
java
View view = findViewById(idขอUIที่เราตั้งไว้ในXML)

ตัวอย่างของ XML 
XML
<androidx.recyclerview.widget.RecyclerView
        //นี้เลย id
        android:id="@+id/Relist"
        //
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
#### Class Relationship
ตามที่เราวางเเผนไว้ใน design pattern เเบบ MVC Class view จะต้องมีความสัมพันธ์เเบบ Has-A กับ Controller
โดยวิธีการง่ายมากโดยการสร้างInstance ขึ้นมาโดยใช้ keyword new
java
//อย่าลืมส่ง this ไปด้วยจะทำให้ Controller หาเราเจอถ้าในภาษา C++ เจ้าthisตัวนี้ก็เปรียบเสมือนการส่งpointerไปให้อีกclassนึงเพื่อclassนั้นๆจะได้accessเข้ามาถึงclassนี้ได้
controller = new Controller(this);

### implement(ฺฺB)
ฟังชั่นนี้มีหน้าที่ในการนำข้อมูลจากการเรียกมาจากModelผ่านControllerเเล้วส่งต่อให้ Adapter ไปประมวลข้อมูลเเล้วสร้าง UI ขึ้นมาใส่ใน Recycleview 
ซึ่งในส่วนนี้ผมจะอธิบายเพิ่มทีหลังเพราะถือว่าเป็นหัวเรื่องใหญ่ในส่วน(Recycleview&Adapter)




## Controller
Controller เปรียบเสมือนตัวกลางทำหน้าที่คล้ายกับLineman หลักๆคือรับเเละส่งข้อมูลในส่วน controller ย้ำว่าไม่ควรมีการเเตะกับข้อมูลโดยตรงเพราะเป็นหน้าที่ของModelเค้า

java
public class Controller implements IController{
    //ความสัมพันธ์เเบบ Has-A Controller - View
    static IView view;
    //ความสัมพันธ์เเบบ Has-A Controller - Model
    static IModel model;
   
   //constructor ก็เหมือนกับ onCreate() activity ใน view
    public Controller(IView view) {
   // รับ address ของ view มาเก็บไว้
        this.view = view;
   //ทำการ initialize ความสัมพันธ์กับ Model
        this.model = new Model(this);
    }
    
    //request ไปที่ model เพื่อที่จะต้องการ data จาก Model 
    public List<coin> GetAllCoin(){ return model.getData();
    
    }
   
   //ส่วนนี้จะอธิบายใน section ในส่วน Model
    @Override
    public void UiInvoke() {
        view.ShowDataOnRecycleView();
    }
}
 จะเห็นได้ว่าส่วนนี้มีความสัมพันธืค่อนข้างเยอะมาก เพราะอย่างที่ผมบอกมันเป็นตัวกลางระหว่าง Model กับ View
 
 ## Model
 ในที่สุดก็มาถึงClassที่มีความสำคัญมากที่สุดหน้าที่สำคัญของมันคือการเชื่อมAPIเเละนำข้อมูลเหล่านั้นมาประมวลตามความต้องการของ user ยกตัวอย่างเช่น
 
python
 //ยกตัวอย่างเป็น python เพราะสั้นเเละเข้าใจง่าย
 Class Model:
   def __init__(self):
     self.IntegersSet = [1,2,3,4]
     self.sumsum = 0
   def sum(self):
    for i in IntegerSet:
     self.sumsum += i
    return self.sumsum 

 

 
 ถ้าเรามี List ของ Integer เราต้องการที่จะรู้ผลรวมของตัวเลขใน List class นี้ก็เป็นหน้าที่ของเค้า 
 
 
java
   public class Model  implements IModel{
     private Retrofit retrofit;
     private API api;
     private Call<Data> jasonAPICall;
     //Database
     private  List<coin> coins;
     //ความสัมพันธ์เเบบ Has-A Model - Controller
     public  IController controller;

    public Model(Controller controller) {
                //initialize controller
                this.controller = controller;
                
                //-----------------ส่วนนี้เป็น 3rd library จะอธิบายเเลกภายหลัง-----------------------
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.coinranking.com/%22)  
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<Data> call = api.getData();
                call.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        objects = response.body().getData().getCoins();
                        if(response.isSuccessful()){
                                sendOutdata(objects);
                                controller.UiInvoke();
                            }
                        }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        //txt.append(t.getMessage());
                        System.out.println("Error");
                    }
                });
              //----------------------------------------------------------------------------

    }
    //Setter Database
    private void setCoins(List<coin> data) {
        objects = data;
    }
    //Getter Database
    @Override
    public List<coin> getCoins() {
        return coins;
    }

}
 
 

 ---
 ### เครื่องมือ(3rd Libraies) 
 - Retofit
 - RecycleView and Adapter
 
 ## Retrofit 2.0
 Retrofit เป็น HTTP Client Library ที่ใช้ติดต่อกับฝั่ง Server ที่ได้รับความนิยมอันดับต้นๆในแอนดรอยด์จากความเรียบง่าย ทำหน้าที่ในการติดต่อกับฝั่งserver เเละได้รับข้อมูลมาในรูปเเบบ Json ต่อมาได้เเปลง
 ข้อมูลเหล่านั้นไปเป็นก้อน object เพื่อให้ง่ายต่อการนำไปใช้กับJava ซึ่งในส่วนนี้มีความคล้ายคลึงกับการเเปลงข้อมูลในบทต่อไปที่กำลังมาถึงใน RecycleView and Adapter เเต่ก่อนอื่นเรามาเรียนรู้วิธีใช้เจ้าตัว retrofit ก่อนเลย
 
```java
//ติดต่อกับ BaseURL เเละ GsonConverterFactory ใช้ในการเเปลงข้อมูลที่ได้รับมาในรูปเเบบ jason ให้เป็น POJO รูปเเบบ class ที่มีชื่อตัวเเปรเหมือนกับ Json รวมทั้งฟังชั่น getter&setter
Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.coinranking.com/%22)  
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<Data> call = api.getData();
                // ทำการ invoke เพื่อใช้ในการdetectข้อมูล
                // ข้อควรระวังคือการ invoke ตรงนี้จะดำเนินเเบบ Async ซึ่งมันจะประมวลผลเเบบ thread คือการรัน code อื่นไปพร้อมกัน CPU ตัวนึงสามารถมีหลาย thread จริงอยู่ที่มันสามารถเพิ่มประสิทธิภาพของ codeได้
                // เเต่ถ้าใช้มันเเบบไม่รู้ มันจะกลายเป็นภับภิบัติได้เหมือนกัน จะเกิดอะไรขึ้นถ้าเรา load data จาก server อยู่เเต่ UI ดันรันข้อมูลไปเเล้วทั้งที่ข้อมูลตัวนั้นไม่ได้มีอยู่จริง เเอพเราจะหลุดทันที
                // ข้อควรทำคือเราควรรันข้อมูลให้เสร็จก่อนหลังจากนั้นค่อย invoke controller ให้ไปบอก View ว่า "เอ่า View ข้อมูลพร้อมเเล้วนะรันข้อมูลได้เลย"
                
                call.enqueue(new Callback<Data>() {
                //OnRespones จะ invoke ก็ต่อเมื่อข้อมูลนั้นมีอยู่จริง
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> resp
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
