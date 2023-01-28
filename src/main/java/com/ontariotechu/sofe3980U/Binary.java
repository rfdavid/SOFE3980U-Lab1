package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
  private String number="0";  // string containing the binary value '0' or '1'


  /**
   * A constructor that generates a binary object.
   *
   * @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
   */
  public Binary(String number) {
    // check if number is binary
    this.number = checkBinary(number);
  }
 
  /**
   * function to check if the input number is in fact a binary 
   * 
   * @return A binary number that has no trailing zeros
   */
  
  private String checkBinary(String number){
    // guard clause for empty string
    if(number=="") { 
      return "0";
    }

    for (int i = 0; i < number.length(); i++) {
      // check each character if it's not 0 or 1
      char ch=number.charAt(i);
      if(ch!='0' && ch!='1') {
        return "0"; // if any char is not 0 or 1, return 0
      }
    }
    
    // remove any trailing zeros before returning
    number = removeTrailingZeros(number);
    return number; // otherwise return the number
  }

  //remove trailing zeros
  private String removeTrailingZeros(String number){
    int beg;
    for (beg = 0; beg < number.length(); beg++) {
      if (number.charAt(beg)!='0')
        break;
    }
    //beg has the index of the first non zero digit in the number
    number=number.substring(beg); // exclude the trailing zeros if any
    return number;
  }


   /**
   * Return the binary value of the variable
   *
   * @return the binary value in a string format.
   */
  public String getValue()
  {
    return this.number;
  }


   /**
   * Return the binary value of the variable
   * @param number the value to set the binary number to
   */
  public void setValue(String number)
  {
    this.number = checkBinary(number);
  }


  /**
   * Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
   *
   * @param num1 The first addend object
   * @param num2 The second addend object
   * @return A binary variable with a value of <i>num1+num2</i>.
   */
  public static Binary add(Binary num1,Binary num2)
  {
    // the index of the first digit of each number
    int ind1=num1.number.length()-1;
    int ind2=num2.number.length()-1;
    //initial variable
    int carry=0;
    String num3="";  // the binary value of the sum
    while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
    {
      int sum=carry; // previous carry
      if(ind1>=0){ // if num1 has a digit to add
        sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
        ind1--; // update ind1
      }
      if(ind2>=0){ // if num2 has a digit to add
        sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
        ind2--; //update ind2
      }
      carry=sum/2; // the new carry
      sum=sum%2;  // the resultant digit
      num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
    }
    Binary result=new Binary(num3);  // create a binary object with the calculated value.
    return result;
  }


  /**
   * Logical Operator OR.
   * Example:
   *   0100 OR 1000 = 1100
   * @param num1 The first number to OR 
   * @param num2 The second number to OR 
   * @return A binary variable with a value of <i>num1 OR num2</i>.
   */
  public static Binary or(Binary num1, Binary num2)
  {
    String num3="";  // the binary value of the sum
    // the index of the first digit of each number
    int ind1=num1.number.length()-1;
    int ind2=num2.number.length()-1;
    int bit; // the result bit after each iteration
    while(ind1>=0 || ind2>=0) // loop until all digits are processed
    {
      // assume the bit is 0, unless prove otherwise (found any 1 bit)
      bit = 0;
      /* check the first number num1
       * if find bit 1, then we assume the result bit is 1
       */
      if(ind1>=0) {
        if (num1.number.charAt(ind1) == '1') {
          bit = 1;
        }
        ind1--;
      }
      /* check the second number num2
       * if find bit 1, then the result bit is 1
       */
      if(ind2>=0) {
        if (num2.number.charAt(ind2) == '1') {
          bit = 1;
        }
        ind2--;
      }
      // append final result to num3
      num3 = bit + num3;
    }
    Binary result=new Binary(num3);
    return result;
  }


  /**
   * Logical Operator AND.
   * Example:
   *   0100 AND 1100 = 0100
   * @param num1 The first number to AND
   * @param num2 The second number to AND
   * @return A binary variable with a value of <i>num1 AND num2</i>.
   */
  public static Binary and(Binary num1, Binary num2)
  {
    String num3="";  // the binary value of the sum
    // the index of the first digit of each number
    int ind1=num1.number.length()-1;
    int ind2=num2.number.length()-1;
    int bit; // the result bit after each iteration
    while(ind1>=0 || ind2>=0) // loop until all digits are processed
    {
      // assume the bit is always 1, until proves otherwise
      bit = 1;
      if (ind1>=0) {
        // bit is 0, and is false
        if (num1.number.charAt(ind1) == '0') {
          bit = 0;
        }
        ind1--;
      } else {
        // first number ended, bit is 0
        bit = 0;
      }
      if (ind2>=0) {
        // bit is 0, and is false
        if (num2.number.charAt(ind2) == '0') {
          bit = 0;
        }
        ind2--;
      } else {
        // second number ended, bit is 0
        bit = 0;
      }
      // append final result to num3
      num3 = "" + bit + num3;
    }
    Binary result=new Binary(num3);
    return result;
  }


  /**
   * Multiply two binary numbers  
   * Example:
   *   0100 X 1100 = 10000
   * @param num1 The first number to multiply 
   * @param num2 The second number to multiply 
   * @return A binary variable with a value of <i>num1 X num2</i>.
   */
  public static Binary mult(Binary num1, Binary num2)
  {
    // the index of the second digit 
    int ind2=num2.number.length()-1;
    Binary num3=new Binary("0");
    while(ind2>=0) // loop the second number 
    {
      // if is 0, do nothing, but if it is 1, sum num1 to num3
      if (num2.number.charAt(ind2) == '1') {
        num3 = Binary.add(num1, num3);
      }
      ind2--;

      // add 0 to num1
      num1.number = num1.number + "0";
    }
    return num3;
  }
}	
