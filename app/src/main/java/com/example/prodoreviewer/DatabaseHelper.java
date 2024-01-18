package com.example.prodoreviewer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table newuser(email TEXT primary key, assessed INTEGER)");
        MyDatabase.execSQL("create Table tblPersonality(email TEXT, personality TEXT, world INTEGER, information INTEGER, decisions INTEGER, structure INTEGER)");
        MyDatabase.execSQL("create Table tblCareer(careerName TEXT primary key, careerDescription TEXT, mbtiType TEXT)");
// ISTJ
        insertCareerData(MyDatabase, "Accountant", "Managing financial records, analyzing budgets, and ensuring financial accuracy. ISTJs are well-suited for roles that require attention to detail and adherence to established procedures.", "ISTJ");
        insertCareerData(MyDatabase, "Civil Engineer", "Designing and overseeing construction projects, ensuring structural integrity, and collaborating with teams. ISTJs excel in roles that demand precision and practical problem-solving.", "ISTJ");
        insertCareerData(MyDatabase, "Medical Administrator", "Managing healthcare facilities, overseeing operations, and ensuring regulatory compliance. ISTJs thrive in roles that require organizational skills and a systematic approach.", "ISTJ");
        insertCareerData(MyDatabase, "Quality Assurance Manager", "Ensuring product or service quality in manufacturing, implementing quality control processes, and conducting audits. ISTJs contribute to maintaining high standards and efficiency.", "ISTJ");

// ISTP
        insertCareerData(MyDatabase, "Mechanical Engineer", "Designing and testing mechanical systems, troubleshooting issues, and collaborating with cross-functional teams. ISTPs excel in hands-on roles that demand innovation and problem-solving.", "ISTP");
        insertCareerData(MyDatabase, "Pilot", "Flying and navigating aircraft, conducting pre-flight checks, and ensuring passenger safety. ISTPs enjoy careers that provide a sense of adventure and require practical skills.", "ISTP");
        insertCareerData(MyDatabase, "Crime Scene Investigator", "Collecting and analyzing forensic evidence, reconstructing crime scenes, and collaborating with law enforcement. ISTPs thrive in roles that demand attention to detail and critical thinking.", "ISTP");
        insertCareerData(MyDatabase, "Outdoor Adventure Guide", "Leading outdoor expeditions, facilitating team-building activities, and ensuring participant safety. ISTPs enjoy careers that allow them to explore and share their love for adventure.", "ISTP");

// ISFJ
        insertCareerData(MyDatabase, "Human Resources Manager", "Managing personnel matters, ensuring employee well-being, and facilitating a positive workplace culture. ISFJs excel in roles that require empathy and interpersonal skills.", "ISFJ");
        insertCareerData(MyDatabase, "Elementary School Teacher", "Educating young students, creating a supportive learning environment, and fostering positive relationships. ISFJs enjoy careers that make a positive impact on others.", "ISFJ");
        insertCareerData(MyDatabase, "Librarian", "Organizing and managing library resources, assisting patrons, and promoting a love for reading. ISFJs thrive in roles that require attention to detail and a service-oriented mindset.", "ISFJ");
        insertCareerData(MyDatabase, "Social Worker", "Supporting individuals and families in need, advocating for social change, and providing resources for improved well-being. ISFJs contribute to creating a more compassionate society.", "ISFJ");

// ISFP
        insertCareerData(MyDatabase, "Graphic Designer", "Creating visual concepts, designing layouts, and producing aesthetically pleasing graphics. ISFPs excel in artistic roles that allow them to express their creativity.", "ISFP");
        insertCareerData(MyDatabase, "Fashion Designer", "Designing clothing and accessories, staying updated on fashion trends, and bringing creative concepts to life. ISFPs enjoy careers that allow them to express their unique style.", "ISFP");
        insertCareerData(MyDatabase, "Landscape Architect", "Planning and designing outdoor spaces, collaborating with clients, and incorporating natural elements into architectural projects. ISFPs contribute to creating visually appealing environments.", "ISFP");
        insertCareerData(MyDatabase, "Chef", "Creating culinary masterpieces, experimenting with flavors, and delighting people's taste buds. ISFPs thrive in roles that allow them to bring artistry to the culinary world.", "ISFP");

// INTJ
        insertCareerData(MyDatabase, "Data Scientist", "Analyzing complex datasets, identifying trends, and providing data-driven insights for decision-making. INTJs excel in roles that require strategic thinking and analytical skills.", "INTJ");
        insertCareerData(MyDatabase, "Architect", "Designing buildings and structures, coordinating with clients, and ensuring functional and aesthetic architectural solutions. INTJs contribute to shaping the physical world with innovative designs.", "INTJ");
        insertCareerData(MyDatabase, "Management Consultant", "Advising organizations on business strategies, process improvements, and organizational effectiveness. INTJs thrive in roles that demand strategic planning and problem-solving.", "INTJ");
        insertCareerData(MyDatabase, "Research Scientist", "Conducting experiments, analyzing results, and contributing to advancements in scientific knowledge. INTJs enjoy careers that allow them to explore the depths of their intellectual curiosity.", "INTJ");

// INTP
        insertCareerData(MyDatabase, "Software Developer", "Creating, testing, and maintaining software applications, utilizing logical thinking and problem-solving skills. INTPs excel in roles that allow them to explore the intricacies of coding and programming.", "INTP");
        insertCareerData(MyDatabase, "Biomedical Researcher", "Conducting research in the field of biomedical science, exploring innovative medical solutions, and contributing to scientific advancements. INTPs thrive in roles that allow them to apply their analytical skills to complex problems.", "INTP");
        insertCareerData(MyDatabase, "University Professor", "Teaching and conducting research in academic settings, sharing knowledge, and inspiring the next generation. INTPs contribute to the academic community with their depth of understanding.", "INTP");
        insertCareerData(MyDatabase, "Environmental Analyst", "Analyzing environmental data, assessing impact assessments, and contributing to sustainable practices. INTPs enjoy careers that allow them to explore and address complex environmental challenges.", "INTP");

// INFJ
        insertCareerData(MyDatabase, "Counselor", "Providing emotional support, guidance, and helping individuals navigate life challenges. INFJs excel in roles that allow them to connect with others on a deep and empathetic level.", "INFJ");
        insertCareerData(MyDatabase, "Nonprofit Manager", "Leading nonprofit organizations, overseeing projects, and advocating for social causes. INFJs contribute to creating positive change and making a difference in their communities.", "INFJ");
        insertCareerData(MyDatabase, "Writer", "Crafting meaningful and inspiring written content, whether in novels, articles, or poetry. INFJs enjoy careers that allow them to express their creativity and convey insightful messages.", "INFJ");
        insertCareerData(MyDatabase, "Humanitarian", "Working in international aid, promoting social justice, and providing assistance to communities in need. INFJs thrive in roles that align with their passion for making the world a better place.", "INFJ");

// INFP
        insertCareerData(MyDatabase, "Psychologist", "Helping individuals understand and manage their emotions, providing therapeutic support, and contributing to mental health. INFPs excel in roles that allow them to make a positive impact on others' well-being.", "INFP");
        insertCareerData(MyDatabase, "Social Media Manager", "Creating and implementing social media strategies, engaging with audiences, and building online communities. INFPs enjoy careers that allow them to express their creativity and connect with people globally.", "INFP");
        insertCareerData(MyDatabase, "Art Therapist", "Using art as a therapeutic medium to help individuals express themselves and address emotional challenges. INFPs contribute to the healing process through creative and empathetic approaches.", "INFP");
        insertCareerData(MyDatabase, "Environmental Activist", "Advocating for environmental conservation, sustainability, and raising awareness about ecological issues. INFPs thrive in roles that align with their passion for making a positive impact on the planet.", "INFP");

// ESTJ
        insertCareerData(MyDatabase, "Business Executive", "Leading organizations, making strategic decisions, and ensuring operational efficiency. ESTJs excel in roles that demand strong leadership and organizational skills.", "ESTJ");
        insertCareerData(MyDatabase, "Military Officer", "Leading military units, planning operations, and ensuring discipline and order. ESTJs contribute to maintaining security and organizational structure.", "ESTJ");
        insertCareerData(MyDatabase, "Project Manager", "Overseeing project development, coordinating tasks, and ensuring timely completion of deliverables. ESTJs thrive in roles that require efficient project management and attention to detail.", "ESTJ");
        insertCareerData(MyDatabase, "Lawyer", "Providing legal counsel, representing clients, and ensuring justice in legal matters. ESTJs excel in roles that demand a strong sense of duty and adherence to rules and regulations.", "ESTJ");

// ESTP
        insertCareerData(MyDatabase, "Entrepreneur", "Starting and running their own business, taking calculated risks, and seizing opportunities. ESTPs thrive in roles that allow them to use their boldness and adaptability.", "ESTP");
        insertCareerData(MyDatabase, "Emergency Medical Technician (EMT)", "Providing immediate medical care in emergency situations, responding to accidents, and ensuring the well-being of patients. ESTPs excel in high-pressure and dynamic environments.", "ESTP");
        insertCareerData(MyDatabase, "Professional Athlete", "Engaging in sports professionally, competing at the highest level, and inspiring fans with their athletic prowess. ESTPs enjoy careers that provide a platform for their physical abilities and competitive spirit.", "ESTP");
        insertCareerData(MyDatabase, "Sales Manager", "Leading sales teams, developing sales strategies, and achieving revenue targets. ESTPs contribute to the success of businesses through their persuasive and goal-oriented approach.", "ESTP");

// ESFJ
        insertCareerData(MyDatabase, "Event Planner", "Organizing and coordinating events, ensuring smooth execution, and creating memorable experiences. ESFJs excel in roles that require interpersonal skills and attention to detail.", "ESFJ");
        insertCareerData(MyDatabase, "Healthcare Administrator", "Managing healthcare facilities, overseeing staff, and ensuring the delivery of quality patient care. ESFJs thrive in roles that allow them to contribute to the well-being of others.", "ESFJ");
        insertCareerData(MyDatabase, "Human Services Worker", "Providing assistance and support to individuals in need, connecting them with resources, and advocating for their well-being. ESFJs contribute to creating a compassionate and supportive society.", "ESFJ");
        insertCareerData(MyDatabase, "Teacher (Primary School)", "Educating young students, fostering a positive learning environment, and building strong connections with pupils. ESFJs enjoy careers that allow them to make a positive impact on the lives of others.", "ESFJ");

// ESFP
        insertCareerData(MyDatabase, "Actor/Actress", "Performing on stage or screen, portraying characters, and entertaining audiences. ESFPs thrive in creative and expressive roles that allow them to showcase their talents.", "ESFP");
        insertCareerData(MyDatabase, "Event Coordinator", "Planning and executing events, managing logistics, and ensuring a memorable experience for attendees. ESFPs excel in roles that combine creativity and organizational skills.", "ESFP");
        insertCareerData(MyDatabase, "Personal Trainer", "Helping individuals achieve fitness goals, providing exercise guidance, and promoting a healthy lifestyle. ESFPs enjoy careers that allow them to combine their passion for fitness with helping others.", "ESFP");
        insertCareerData(MyDatabase, "Travel Agent", "Assisting individuals in planning and booking travel experiences, providing recommendations, and ensuring a seamless journey. ESFPs contribute to creating unforgettable travel adventures.", "ESFP");

// ENTJ
        insertCareerData(MyDatabase, "CEO (Chief Executive Officer)", "Leading and directing organizations, making high-level decisions, and setting strategic goals. ENTJs excel in roles that demand strong leadership and vision.", "ENTJ");
        insertCareerData(MyDatabase, "Politician", "Holding public office, shaping policies, and advocating for the interests of constituents. ENTJs contribute to the political landscape with their strategic thinking and determination.", "ENTJ");
        insertCareerData(MyDatabase, "Management Analyst", "Analyzing organizational efficiency, recommending improvements, and implementing strategic changes. ENTJs thrive in roles that require them to optimize processes and drive results.", "ENTJ");
        insertCareerData(MyDatabase, "Judge", "Presiding over legal proceedings, interpreting laws, and ensuring fair and just decisions. ENTJs excel in roles that demand integrity, decisiveness, and a commitment to justice.", "ENTJ");

// ENTP
        insertCareerData(MyDatabase, "Innovations Manager", "Leading innovation initiatives, fostering creative thinking, and implementing new ideas within organizations. ENTPs excel in roles that allow them to explore and experiment with novel concepts.", "ENTP");
        insertCareerData(MyDatabase, "Research and Development Scientist", "Conducting research to develop new products, technologies, or scientific breakthroughs. ENTPs contribute to advancements in various fields through their curiosity and inventive thinking.", "ENTP");
        insertCareerData(MyDatabase, "Venture Capitalist", "Investing in startup businesses, providing funding, and guiding entrepreneurs in growing their ventures. ENTPs thrive in roles that involve strategic decision-making and exploring opportunities in the business world.", "ENTP");
        insertCareerData(MyDatabase, "Political Strategist", "Developing and implementing strategies for political campaigns, shaping public opinion, and advising political candidates. ENTPs contribute to the political landscape with their analytical thinking and strategic planning.", "ENTP");

// ENFJ
        insertCareerData(MyDatabase, "Life Coach", "Guiding individuals in personal and professional development, helping them set and achieve goals. ENFJs excel in roles that allow them to inspire and empower others.", "ENFJ");
        insertCareerData(MyDatabase, "Public Relations Specialist", "Managing communication between organizations and the public, crafting messages, and building a positive public image. ENFJs thrive in roles that involve interpersonal skills and relationship-building.", "ENFJ");
        insertCareerData(MyDatabase, "Community Organizer", "Mobilizing communities, advocating for social change, and coordinating initiatives for the greater good. ENFJs contribute to creating positive impact and fostering unity.", "ENFJ");
        insertCareerData(MyDatabase, "Hospitality Manager", "Overseeing hospitality services, ensuring guest satisfaction, and managing operations in hotels or resorts. ENFJs enjoy roles that allow them to create welcoming and positive environments.", "ENFJ");

// ENFP
        insertCareerData(MyDatabase, "Marketing Specialist", "Developing and implementing marketing strategies, creating engaging campaigns, and connecting with target audiences. ENFPs excel in roles that allow them to express creativity and connect with people.", "ENFP");
        insertCareerData(MyDatabase, "Public Speaker", "Inspiring and motivating audiences through public speaking engagements, addressing various topics. ENFPs contribute to positive change by sharing their passion and insights.", "ENFP");
        insertCareerData(MyDatabase, "Travel Writer", "Exploring destinations and sharing travel experiences through writing, engaging readers with captivating stories. ENFPs thrive in roles that allow them to combine their love for adventure with creative expression.", "ENFP");
        insertCareerData(MyDatabase, "Social Entrepreneur", "Starting ventures that address social issues, contributing to positive change and making a difference. ENFPs enjoy careers that align with their values and allow them to create a better world.", "ENFP");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists allusers");
        MyDatabase.execSQL("drop Table if exists newuser");
        MyDatabase.execSQL("drop Table if exists tblPersonality");
        MyDatabase.execSQL("drop Table if exists tblCareer");

        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table newuser(email TEXT primary key, assessed INTEGER)");
        MyDatabase.execSQL("create Table tblPersonality(email TEXT, personality TEXT, world INTEGER, information INTEGER, decisions INTEGER, structure INTEGER)");
        MyDatabase.execSQL("create Table tblCareer(careerName TEXT primary key, careerDescription TEXT, mbtiType TEXT)");
// ISTJ
        insertCareerData(MyDatabase, "Accountant", "Managing financial records, analyzing budgets, and ensuring financial accuracy. ISTJs are well-suited for roles that require attention to detail and adherence to established procedures.", "ISTJ");
        insertCareerData(MyDatabase, "Civil Engineer", "Designing and overseeing construction projects, ensuring structural integrity, and collaborating with teams. ISTJs excel in roles that demand precision and practical problem-solving.", "ISTJ");
        insertCareerData(MyDatabase, "Medical Administrator", "Managing healthcare facilities, overseeing operations, and ensuring regulatory compliance. ISTJs thrive in roles that require organizational skills and a systematic approach.", "ISTJ");
        insertCareerData(MyDatabase, "Quality Assurance Manager", "Ensuring product or service quality in manufacturing, implementing quality control processes, and conducting audits. ISTJs contribute to maintaining high standards and efficiency.", "ISTJ");

// ISTP
        insertCareerData(MyDatabase, "Mechanical Engineer", "Designing and testing mechanical systems, troubleshooting issues, and collaborating with cross-functional teams. ISTPs excel in hands-on roles that demand innovation and problem-solving.", "ISTP");
        insertCareerData(MyDatabase, "Pilot", "Flying and navigating aircraft, conducting pre-flight checks, and ensuring passenger safety. ISTPs enjoy careers that provide a sense of adventure and require practical skills.", "ISTP");
        insertCareerData(MyDatabase, "Crime Scene Investigator", "Collecting and analyzing forensic evidence, reconstructing crime scenes, and collaborating with law enforcement. ISTPs thrive in roles that demand attention to detail and critical thinking.", "ISTP");
        insertCareerData(MyDatabase, "Outdoor Adventure Guide", "Leading outdoor expeditions, facilitating team-building activities, and ensuring participant safety. ISTPs enjoy careers that allow them to explore and share their love for adventure.", "ISTP");

// ISFJ
        insertCareerData(MyDatabase, "Human Resources Manager", "Managing personnel matters, ensuring employee well-being, and facilitating a positive workplace culture. ISFJs excel in roles that require empathy and interpersonal skills.", "ISFJ");
        insertCareerData(MyDatabase, "Elementary School Teacher", "Educating young students, creating a supportive learning environment, and fostering positive relationships. ISFJs enjoy careers that make a positive impact on others.", "ISFJ");
        insertCareerData(MyDatabase, "Librarian", "Organizing and managing library resources, assisting patrons, and promoting a love for reading. ISFJs thrive in roles that require attention to detail and a service-oriented mindset.", "ISFJ");
        insertCareerData(MyDatabase, "Social Worker", "Supporting individuals and families in need, advocating for social change, and providing resources for improved well-being. ISFJs contribute to creating a more compassionate society.", "ISFJ");

// ISFP
        insertCareerData(MyDatabase, "Graphic Designer", "Creating visual concepts, designing layouts, and producing aesthetically pleasing graphics. ISFPs excel in artistic roles that allow them to express their creativity.", "ISFP");
        insertCareerData(MyDatabase, "Fashion Designer", "Designing clothing and accessories, staying updated on fashion trends, and bringing creative concepts to life. ISFPs enjoy careers that allow them to express their unique style.", "ISFP");
        insertCareerData(MyDatabase, "Landscape Architect", "Planning and designing outdoor spaces, collaborating with clients, and incorporating natural elements into architectural projects. ISFPs contribute to creating visually appealing environments.", "ISFP");
        insertCareerData(MyDatabase, "Chef", "Creating culinary masterpieces, experimenting with flavors, and delighting people's taste buds. ISFPs thrive in roles that allow them to bring artistry to the culinary world.", "ISFP");

// INTJ
        insertCareerData(MyDatabase, "Data Scientist", "Analyzing complex datasets, identifying trends, and providing data-driven insights for decision-making. INTJs excel in roles that require strategic thinking and analytical skills.", "INTJ");
        insertCareerData(MyDatabase, "Architect", "Designing buildings and structures, coordinating with clients, and ensuring functional and aesthetic architectural solutions. INTJs contribute to shaping the physical world with innovative designs.", "INTJ");
        insertCareerData(MyDatabase, "Management Consultant", "Advising organizations on business strategies, process improvements, and organizational effectiveness. INTJs thrive in roles that demand strategic planning and problem-solving.", "INTJ");
        insertCareerData(MyDatabase, "Research Scientist", "Conducting experiments, analyzing results, and contributing to advancements in scientific knowledge. INTJs enjoy careers that allow them to explore the depths of their intellectual curiosity.", "INTJ");

// INTP
        insertCareerData(MyDatabase, "Software Developer", "Creating, testing, and maintaining software applications, utilizing logical thinking and problem-solving skills. INTPs excel in roles that allow them to explore the intricacies of coding and programming.", "INTP");
        insertCareerData(MyDatabase, "Biomedical Researcher", "Conducting research in the field of biomedical science, exploring innovative medical solutions, and contributing to scientific advancements. INTPs thrive in roles that allow them to apply their analytical skills to complex problems.", "INTP");
        insertCareerData(MyDatabase, "University Professor", "Teaching and conducting research in academic settings, sharing knowledge, and inspiring the next generation. INTPs contribute to the academic community with their depth of understanding.", "INTP");
        insertCareerData(MyDatabase, "Environmental Analyst", "Analyzing environmental data, assessing impact assessments, and contributing to sustainable practices. INTPs enjoy careers that allow them to explore and address complex environmental challenges.", "INTP");

// INFJ
        insertCareerData(MyDatabase, "Counselor", "Providing emotional support, guidance, and helping individuals navigate life challenges. INFJs excel in roles that allow them to connect with others on a deep and empathetic level.", "INFJ");
        insertCareerData(MyDatabase, "Nonprofit Manager", "Leading nonprofit organizations, overseeing projects, and advocating for social causes. INFJs contribute to creating positive change and making a difference in their communities.", "INFJ");
        insertCareerData(MyDatabase, "Writer", "Crafting meaningful and inspiring written content, whether in novels, articles, or poetry. INFJs enjoy careers that allow them to express their creativity and convey insightful messages.", "INFJ");
        insertCareerData(MyDatabase, "Humanitarian", "Working in international aid, promoting social justice, and providing assistance to communities in need. INFJs thrive in roles that align with their passion for making the world a better place.", "INFJ");

// INFP
        insertCareerData(MyDatabase, "Psychologist", "Helping individuals understand and manage their emotions, providing therapeutic support, and contributing to mental health. INFPs excel in roles that allow them to make a positive impact on others' well-being.", "INFP");
        insertCareerData(MyDatabase, "Social Media Manager", "Creating and implementing social media strategies, engaging with audiences, and building online communities. INFPs enjoy careers that allow them to express their creativity and connect with people globally.", "INFP");
        insertCareerData(MyDatabase, "Art Therapist", "Using art as a therapeutic medium to help individuals express themselves and address emotional challenges. INFPs contribute to the healing process through creative and empathetic approaches.", "INFP");
        insertCareerData(MyDatabase, "Environmental Activist", "Advocating for environmental conservation, sustainability, and raising awareness about ecological issues. INFPs thrive in roles that align with their passion for making a positive impact on the planet.", "INFP");

// ESTJ
        insertCareerData(MyDatabase, "Business Executive", "Leading organizations, making strategic decisions, and ensuring operational efficiency. ESTJs excel in roles that demand strong leadership and organizational skills.", "ESTJ");
        insertCareerData(MyDatabase, "Military Officer", "Leading military units, planning operations, and ensuring discipline and order. ESTJs contribute to maintaining security and organizational structure.", "ESTJ");
        insertCareerData(MyDatabase, "Project Manager", "Overseeing project development, coordinating tasks, and ensuring timely completion of deliverables. ESTJs thrive in roles that require efficient project management and attention to detail.", "ESTJ");
        insertCareerData(MyDatabase, "Lawyer", "Providing legal counsel, representing clients, and ensuring justice in legal matters. ESTJs excel in roles that demand a strong sense of duty and adherence to rules and regulations.", "ESTJ");

// ESTP
        insertCareerData(MyDatabase, "Entrepreneur", "Starting and running their own business, taking calculated risks, and seizing opportunities. ESTPs thrive in roles that allow them to use their boldness and adaptability.", "ESTP");
        insertCareerData(MyDatabase, "Emergency Medical Technician (EMT)", "Providing immediate medical care in emergency situations, responding to accidents, and ensuring the well-being of patients. ESTPs excel in high-pressure and dynamic environments.", "ESTP");
        insertCareerData(MyDatabase, "Professional Athlete", "Engaging in sports professionally, competing at the highest level, and inspiring fans with their athletic prowess. ESTPs enjoy careers that provide a platform for their physical abilities and competitive spirit.", "ESTP");
        insertCareerData(MyDatabase, "Sales Manager", "Leading sales teams, developing sales strategies, and achieving revenue targets. ESTPs contribute to the success of businesses through their persuasive and goal-oriented approach.", "ESTP");

// ESFJ
        insertCareerData(MyDatabase, "Event Planner", "Organizing and coordinating events, ensuring smooth execution, and creating memorable experiences. ESFJs excel in roles that require interpersonal skills and attention to detail.", "ESFJ");
        insertCareerData(MyDatabase, "Healthcare Administrator", "Managing healthcare facilities, overseeing staff, and ensuring the delivery of quality patient care. ESFJs thrive in roles that allow them to contribute to the well-being of others.", "ESFJ");
        insertCareerData(MyDatabase, "Human Services Worker", "Providing assistance and support to individuals in need, connecting them with resources, and advocating for their well-being. ESFJs contribute to creating a compassionate and supportive society.", "ESFJ");
        insertCareerData(MyDatabase, "Teacher (Primary School)", "Educating young students, fostering a positive learning environment, and building strong connections with pupils. ESFJs enjoy careers that allow them to make a positive impact on the lives of others.", "ESFJ");

// ESFP
        insertCareerData(MyDatabase, "Actor/Actress", "Performing on stage or screen, portraying characters, and entertaining audiences. ESFPs thrive in creative and expressive roles that allow them to showcase their talents.", "ESFP");
        insertCareerData(MyDatabase, "Event Coordinator", "Planning and executing events, managing logistics, and ensuring a memorable experience for attendees. ESFPs excel in roles that combine creativity and organizational skills.", "ESFP");
        insertCareerData(MyDatabase, "Personal Trainer", "Helping individuals achieve fitness goals, providing exercise guidance, and promoting a healthy lifestyle. ESFPs enjoy careers that allow them to combine their passion for fitness with helping others.", "ESFP");
        insertCareerData(MyDatabase, "Travel Agent", "Assisting individuals in planning and booking travel experiences, providing recommendations, and ensuring a seamless journey. ESFPs contribute to creating unforgettable travel adventures.", "ESFP");

// ENTJ
        insertCareerData(MyDatabase, "CEO (Chief Executive Officer)", "Leading and directing organizations, making high-level decisions, and setting strategic goals. ENTJs excel in roles that demand strong leadership and vision.", "ENTJ");
        insertCareerData(MyDatabase, "Politician", "Holding public office, shaping policies, and advocating for the interests of constituents. ENTJs contribute to the political landscape with their strategic thinking and determination.", "ENTJ");
        insertCareerData(MyDatabase, "Management Analyst", "Analyzing organizational efficiency, recommending improvements, and implementing strategic changes. ENTJs thrive in roles that require them to optimize processes and drive results.", "ENTJ");
        insertCareerData(MyDatabase, "Judge", "Presiding over legal proceedings, interpreting laws, and ensuring fair and just decisions. ENTJs excel in roles that demand integrity, decisiveness, and a commitment to justice.", "ENTJ");

// ENTP
        insertCareerData(MyDatabase, "Innovations Manager", "Leading innovation initiatives, fostering creative thinking, and implementing new ideas within organizations. ENTPs excel in roles that allow them to explore and experiment with novel concepts.", "ENTP");
        insertCareerData(MyDatabase, "Research and Development Scientist", "Conducting research to develop new products, technologies, or scientific breakthroughs. ENTPs contribute to advancements in various fields through their curiosity and inventive thinking.", "ENTP");
        insertCareerData(MyDatabase, "Venture Capitalist", "Investing in startup businesses, providing funding, and guiding entrepreneurs in growing their ventures. ENTPs thrive in roles that involve strategic decision-making and exploring opportunities in the business world.", "ENTP");
        insertCareerData(MyDatabase, "Political Strategist", "Developing and implementing strategies for political campaigns, shaping public opinion, and advising political candidates. ENTPs contribute to the political landscape with their analytical thinking and strategic planning.", "ENTP");

// ENFJ
        insertCareerData(MyDatabase, "Life Coach", "Guiding individuals in personal and professional development, helping them set and achieve goals. ENFJs excel in roles that allow them to inspire and empower others.", "ENFJ");
        insertCareerData(MyDatabase, "Public Relations Specialist", "Managing communication between organizations and the public, crafting messages, and building a positive public image. ENFJs thrive in roles that involve interpersonal skills and relationship-building.", "ENFJ");
        insertCareerData(MyDatabase, "Community Organizer", "Mobilizing communities, advocating for social change, and coordinating initiatives for the greater good. ENFJs contribute to creating positive impact and fostering unity.", "ENFJ");
        insertCareerData(MyDatabase, "Hospitality Manager", "Overseeing hospitality services, ensuring guest satisfaction, and managing operations in hotels or resorts. ENFJs enjoy roles that allow them to create welcoming and positive environments.", "ENFJ");

// ENFP
        insertCareerData(MyDatabase, "Marketing Specialist", "Developing and implementing marketing strategies, creating engaging campaigns, and connecting with target audiences. ENFPs excel in roles that allow them to express creativity and connect with people.", "ENFP");
        insertCareerData(MyDatabase, "Public Speaker", "Inspiring and motivating audiences through public speaking engagements, addressing various topics. ENFPs contribute to positive change by sharing their passion and insights.", "ENFP");
        insertCareerData(MyDatabase, "Travel Writer", "Exploring destinations and sharing travel experiences through writing, engaging readers with captivating stories. ENFPs thrive in roles that allow them to combine their love for adventure with creative expression.", "ENFP");
        insertCareerData(MyDatabase, "Social Entrepreneur", "Starting ventures that address social issues, contributing to positive change and making a difference. ENFPs enjoy careers that align with their values and allow them to create a better world.", "ENFP");

    }

    public boolean insertData(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("Password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);
        if(result == -1){
            return false;
        } else {
            ContentValues newUserValues = new ContentValues();
            newUserValues.put("email", email);
            newUserValues.put("assessed", 0);
            MyDatabase.insert("newuser", null, newUserValues);
            return true;
        }
    }

    public boolean insertPersonalityData(String email, String personality, int world, int information, int decisions, int structure) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("personality", personality);
        contentValues.put("world", world);
        contentValues.put("information", information);
        contentValues.put("decisions", decisions);
        contentValues.put("structure", structure);

        Cursor cursor = MyDatabase.query("tblPersonality", null, "email=?", new String[]{email}, null, null, null);
        if (cursor.getCount() > 0) {
            int updatedRows = MyDatabase.update("tblPersonality", contentValues, "email=?", new String[]{email});
            cursor.close();
            if (updatedRows == 0) {
                return false;
            } else {
                userAssess(email);
                return true;
            }
        } else {
            long result = MyDatabase.insert("tblPersonality", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                userAssess(email);
                return true;
            }
        }
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?",new String[]{email});

        if(cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from newuser where email = ?",new String[]{email});

        if(cursor.getCount() == 0) {
            ContentValues newUserValues = new ContentValues();
            newUserValues.put("email", email);
            newUserValues.put("assessed", 0);
            MyDatabase.insert("newuser", null, newUserValues);
        }

        cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?",new String[]{email,password});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void userAssess(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("assessed", 1);
        MyDatabase.update("newuser", contentValues, "email = ?", new String[]{email});
    }

    public boolean hasAssessed(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from newuser where email = ? and assessed = 1",new String[]{email});
        if(cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getPersonality(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select personality from tblPersonality where email = ?",new String[]{email});
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndexOrThrow("personality"));
        }
        return null;
    }

    public int getPercentage(String email, String personalityTrait) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select " + personalityTrait + " from tblPersonality where email = ?",new String[]{email});
        if(cursor.moveToFirst()){
            return cursor.getInt(cursor.getColumnIndexOrThrow(personalityTrait));
        }
        return -1;
    }

    private void insertCareerData(SQLiteDatabase MyDatabase, String careerName, String careerDescription, String mbtiType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("careerName", careerName);
        contentValues.put("careerDescription", careerDescription);
        contentValues.put("mbtiType", mbtiType);

        MyDatabase.insert("tblCareer", null, contentValues);
    }

    public String getCareerDetails(String careerName) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select careerDescription from tblCareer where careerName = ?", new String[]{careerName});

        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndexOrThrow("careerDescription"));
        }
        return null;
    }

    public List<CareerPath> getAllCareerPaths(String mbtiType) {
        List<CareerPath> careerPaths = new ArrayList<>();

        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String[] columns = {"careerName", "careerDescription"};
        String selection = "mbtiType=?";
        String[] selectionArgs = {mbtiType};

        Cursor cursor = MyDatabase.query("tblCareer", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String careerName = cursor.getString(cursor.getColumnIndexOrThrow("careerName"));
                String careerDescription = cursor.getString(cursor.getColumnIndexOrThrow("careerDescription"));

                // Assuming CareerPath has a constructor that takes name and description
                CareerPath careerPath = new CareerPath(careerName, careerDescription);
                careerPaths.add(careerPath);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return careerPaths;
    }

}
