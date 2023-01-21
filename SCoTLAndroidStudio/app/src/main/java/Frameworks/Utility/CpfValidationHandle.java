package Frameworks.Utility;

import android.widget.EditText;

import Policy.Adapters.MyLog;
import Utility.ChainOfResponsibilityHandle;
import Utility.Func2;

public class CpfValidationHandle
        extends
            ChainOfResponsibilityHandle<Boolean>
{
    private final EditText m_text;

    public CpfValidationHandle(EditText text)
    {
        m_text = text;
    }

    public Boolean Validate()
    {
        String cpfString = m_text.getText().toString();

        if(cpfString.isEmpty())
        {
            MyLog.LogMessage("No CPF");
            return false;
        }

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (
                cpfString.equals("00000000000") || cpfString.equals("11111111111") ||
                cpfString.equals("22222222222") || cpfString.equals("33333333333") ||
                cpfString.equals("44444444444") || cpfString.equals("55555555555") ||
                cpfString.equals("66666666666") || cpfString.equals("77777777777") ||
                cpfString.equals("88888888888") || cpfString.equals("99999999999") ||
                cpfString.length() != 11
        )
        {
            MyLog.LogMessage("Invalid cpf");
            return false;
        }

        // cpf, peso, primeiro?,resultado
        Func2<String, Boolean, Integer> getVerifyingDigit = (cpf, isFirst) ->
        {
            int sum = 0;
            int currentWeight = (isFirst ? 10 : 11);
            for(int i = 0; i < (isFirst ? 9 : 10); ++i)
            {
                int num = (cpf.charAt(i) - 48);
                sum += num * currentWeight;
                --currentWeight;
            }

            int restOfDivision = sum % 11;
            return restOfDivision < 2 ? 0 : 11 - restOfDivision;
        };

        try
        {
            char first = (char)(getVerifyingDigit.Invoke(cpfString, true) + 48);
            char second = (char)(getVerifyingDigit.Invoke(cpfString, false) + 48);

            if (first == cpfString.charAt(9) && second == cpfString.charAt(10))
            {
                if(m_next != null)
                {
                    return m_next.Validate();
                }

                return true;
            }

            MyLog.LogMessage("Invalid cpf");
            return false;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
