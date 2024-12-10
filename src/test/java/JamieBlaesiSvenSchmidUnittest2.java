import org.example.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JamieBlaesiSvenSchmidUnittest2 {

    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    // Expanded tests for isPasswordValid with edge cases and boundaries
    @Test
    void testIsPasswordValid() {
        assertTrue(passwordValidator.isPasswordValid("StrongP@ssword1"), "Valid password should pass.");
        assertFalse(passwordValidator.isPasswordValid("short"), "Password too short should fail.");
        assertFalse(passwordValidator.isPasswordValid("passwordwithnospaces123"), "Password without uppercase or special characters should fail.");
        assertFalse(passwordValidator.isPasswordValid("TOO_LONG_PASSWORD_WITH_SPECIAL@123"), "Password exceeding max length should fail.");
        assertFalse(passwordValidator.isPasswordValid(null), "Null password should fail.");
        assertFalse(passwordValidator.isPasswordValid(" "), "Empty password or spaces only should fail.");
    }

    // Boundary tests for min/max length
    @Test
    void testIsPasswordValidBoundaries() {
        assertTrue(passwordValidator.isPasswordValid("Abcdef@1"), "Password with exactly 8 characters should pass.");
        assertTrue(passwordValidator.isPasswordValid("AbcdefghijKlmnopq@1"), "Password with exactly 20 characters should pass.");
        assertFalse(passwordValidator.isPasswordValid("Abc@1"), "Password shorter than 8 characters should fail.");
        assertFalse(passwordValidator.isPasswordValid("AbcdefghijKlmnoPqrstuvwxyz@1"), "Password longer than 20 characters should fail.");
    }

    // Expanded tests for hasNumeric
    @Test
    void testHasNumeric() {
        assertTrue(passwordValidator.hasNumeric("NoDigitsHere!"), "Password without numbers should pass numeric check.");
        assertFalse(passwordValidator.hasNumeric("Contains1Digit"), "Password with numbers should fail numeric check.");
    }

    // Expanded tests for hasLowercase
    @Test
    void testHasLowercase() {
        assertTrue(passwordValidator.hasLowercase("ALLUPPER123!"), "Password without lowercase letters should pass lowercase check.");
        assertFalse(passwordValidator.hasLowercase("lowercaseOnly"), "Password with lowercase letters should fail lowercase check.");
    }

    // Expanded tests for hasUppercase
    @Test
    void testHasUppercase() {
        assertTrue(passwordValidator.hasUppercase("nouppercase123!"), "Password without uppercase letters should pass uppercase check.");
        assertFalse(passwordValidator.hasUppercase("ContainsUppercase"), "Password with uppercase letters should fail uppercase check.");
    }

    // Expanded tests for hasSpecial with mocking Pattern
    @Test
    void testHasSpecialWithMockedPattern() {
        // ensure that the static mocking of java.util.regex.Pattern using Mockito is properly managed
        try (MockedStatic<Pattern> mockedPattern = mockStatic(Pattern.class)) {
            Pattern mockPattern = mock(Pattern.class);
            Matcher mockMatcher = mock(Matcher.class);

            when(Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")).thenReturn(mockPattern);
            when(mockPattern.matcher("Special@Char")).thenReturn(mockMatcher);
            when(mockMatcher.find()).thenReturn(true);

            boolean result = passwordValidator.hasSpecial("Special@Char");
            assertFalse(result, "Password with special character should fail hasSpecial check.");

            verify(mockPattern).matcher("Special@Char");
            verify(mockMatcher).find();
        }
    }

    // Test with no special character and mocked Pattern
    @Test
    void testHasSpecialWithNoSpecialChar() {
        // ensure that the static mocking of java.util.regex.Pattern using Mockito is properly managed
        try (MockedStatic<Pattern> mockedPattern = mockStatic(Pattern.class)) {
            Pattern mockPattern = mock(Pattern.class);
            Matcher mockMatcher = mock(Matcher.class);

            when(Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")).thenReturn(mockPattern);
            when(mockPattern.matcher("NoSpecialChar123")).thenReturn(mockMatcher);
            when(mockMatcher.find()).thenReturn(false);

            boolean result = passwordValidator.hasSpecial("NoSpecialChar123");
            assertTrue(result, "Password without special character should pass hasSpecial check.");

            verify(mockPattern).matcher("NoSpecialChar123");
            verify(mockMatcher).find();
        }
    }
}
