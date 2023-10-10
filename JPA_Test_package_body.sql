create or replace package body JPA_Test is

  procedure records_number(quantity OUT NUMBER) is
  begin
  
    select count(*) into quantity from FAQ_QUESTION_ANSWER;
  end records_number;

end JPA_Test;
/
