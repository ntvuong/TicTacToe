// Nguyen Tien Vuong
//
// DYUQFJ
//
// Assignment2
//
// 2018/11/21 08:07:14
//
// This solution was submitted and prepared by Nguyen Tien Vuong, DYUQFJ for the
// Assignment2 assignment of the Practical software engineering I. course.
//
// I declare that this solution is my own work.
//
// I have not copied or used third party solutions.
//
// I have not passed my solution to my classmates, neither  made it public.
//
// Students’ regulation of Eötvös Loránd University (ELTE Regulations
// Vol. II. 74/C. § ) states that as long as a student presents another
// student’s work - or at least the significant part of it - as his/her own
// performance, it will count as a disciplinary fault. The most serious
// consequence of a disciplinary fault can be dismissal of the student from
// the University.

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Vuong Nguyen
 */
public class PlayerXO {
    private int row;
    private int column;
    
    public PlayerXO(int row, int column){
        this.row = row;
        this.column = column;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public void setColumn(int column){
        this.column = column;
    }
}
